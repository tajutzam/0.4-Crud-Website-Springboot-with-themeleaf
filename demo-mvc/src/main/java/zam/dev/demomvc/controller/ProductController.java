package zam.dev.demomvc.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.var;
import zam.dev.demomvc.dto.ProductDto;
import zam.dev.demomvc.dto.SearchKeyword;
import zam.dev.demomvc.model.entity.Product;
import zam.dev.demomvc.service.ProductService;

@Controller
@RequestMapping("")
public class ProductController {
    

    // autowired untuk incldude dependency injection
    @Autowired
    private ProductService productService;

    // model mapper untuk map ke sebuah class untuk dijadikkan object
    @Autowired
    private ModelMapper modelMapper;

    // get map ke depan , yaitu indek 
    @GetMapping("/")
    public String findAll(Model model){
    // asign ke sebuah variable , iterable agar bisa dijadikan foreach
       Iterable<Product> productAll = productService.findAll();

    // add atribute dengan nama nama yang ada pada "" , dengan value yang ada di samping nya 
       model.addAttribute("searchFrom", new SearchKeyword());
       model.addAttribute("products", productAll);
       model.addAttribute("total_product", productService.count());
    //return ke halaman html indek   
       return "indek";
    }
    
    // get maping ke html add , 
    @GetMapping("/add")
    public String getFormAdd(Model model){
        // add atribute dengan nama nama yang ada pada "" , dengan value yang ada di samping nya 
        model.addAttribute("product", new ProductDto());
        model.addAttribute("searchFrom", new SearchKeyword());
        return "add";
    }

    @PostMapping("/product/save")
    public String save(Model model , @Valid ProductDto productDto , BindingResult eror){
        // belum bisa , harus belajar dlu untuk menampilkan error ke front end menggunakan themeleaf
        if(eror.hasErrors()){
            model.addAttribute("error", eror.getAllErrors());    
        }
        // mapper ke object product
        Product product = modelMapper.map(productDto, Product.class);
        productService.addProduct(product);
        // add atribute product yang sudah di add
        model.addAttribute("product", product);
        return "redirect:/";
    }

    // delete product by id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        // delete product by id , 
        productService.delete(id);
        // return ke halaman awal
        return "redirect:/";
    }

    // get maping untuk form edit
    @GetMapping("/edit/{id}")
    public String getFormEdit(Model model , @PathVariable("id") Long id){
        // mencari id yang ingin di edit
        model.addAttribute("product", productService.findById(id));
        // karena di edit ada form seacrh , tambahkan object search kosong
        model.addAttribute("searchFrom", new SearchKeyword());
        //lempar ke depan
        return "edit";
    }

    // update function
    @PostMapping("/update")
    public String update(Model model , Product product){
        //update service function
        productService.updateProduct(product);
        // lalu return ke awal
        return "redirect:/";
    }

    // seacrh function
    @PostMapping("/search")
    public String findByName(Model model , SearchKeyword searchKeyword ){
       var productMatch = productService.findByName(searchKeyword.getKeyword());
    //    seacrh form dimasukan ke dalam atribut
       model.addAttribute("searchFrom", searchKeyword);
       //product ketemu akan di tampilkan , melalu variable product yang ada pada iterable themeleaf
       model.addAttribute("products", productMatch);
       return "indek";
    }

    
}
