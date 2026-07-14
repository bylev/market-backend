package mx.edu.tecdesoftware.market_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend.domain.service.Product;
import mx.edu.tecdesoftware.market_backend.domain.service.ProductService;
import mx.edu.tecdesoftware.market_backend.persistence.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description= "Manage products in the store")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("") // Get mapping general
    @Operation(summary = "Get all products",  description = "Return a list of available products")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK); //Respuesta en HTTP
    }

    @GetMapping("/{id}") // Id del producto
    @Operation(summary="Get product by Id", description = "Return a product by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> getById(    @Parameter(description="ID of the product retrieved", example = "7", required = true)
                                                   @PathVariable("id") int productId) {
        return productService.getProductById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener por Id
    @GetMapping("/category/{categoryId}")
    @Operation(summary= "Get a product by a category")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(@Parameter(description="ID of the category retrieved", example = "2", required = true)
                                                           @PathVariable int categoryId) {

        return productService.getByCategory(categoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Guardar un nuevo producto
    @PostMapping
    @Operation(summary="Add a new product", description = "Register a new product and return the created product", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example product",
                            value = """
                                    {
                                        "name": "Mirinda",
                                        "categoryId": "5",
                                        "price": "20.50",
                                        "stock": 300,
                                        "active": true
                                    }
                                    """
                    )
            )
    ))
    @ApiResponse(responseCode = "201", description = "Successful creation of product")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@Parameter(description = "")@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete a product by its ID", description = "Delete a product if it exists")
    @ApiResponse(responseCode = "200", description = "Successful delete of product")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> delete(@Parameter(description = "ID of the product to be deleted", example = "2", required = true) @PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }




}
