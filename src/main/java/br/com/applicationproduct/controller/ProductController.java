package br.com.applicationproduct.controller;

import br.com.applicationproduct.entity.Product;
import br.com.applicationproduct.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Products")
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Cadastrar Produto")
    @PostMapping
    public ResponseEntity<Product> createProduct (@Valid @RequestBody Product product) throws Exception{
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todos os Produtos")
    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts (){
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @ApiOperation(value = "Listar Produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @ApiOperation(value = "Atualizar Produto")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        return new ResponseEntity<>(productService.updateProducts(product, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar Produto")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }



}
