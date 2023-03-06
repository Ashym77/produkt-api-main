package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductRepositoryTest {


    @Autowired // för att kunna instantansiera
    private ProductRepository underTest;


    private ProductRepository repository;


    @Test
    void testfindbycategoryWhengiveAnExistingCAtegoryThenTeturnNotEmpty() {
//given
        String category = "jewelery";

        Product product = new Product("diamond ring", 2000.44, category,
                " expensive ring ", "diamondring");

        underTest.save(product);



        List<Product> result = underTest.findByCategory(category);

        Assertions.assertAll(

                () -> assertFalse(result.isEmpty()),
                () -> assertEquals("jewelery",result.get(0).getCategory())


        );
    }









    @Test
    void whenSerchingForExistingTitle_thenReturnTruee() {
        //given
        String title = "en dator";
        Product product = new Product(title
                , 25000.0,
                "electronic", "bra att ha", "urltillbild");
        underTest.save(product);

        // when
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        //then
        Assertions.assertAll(
                () -> assertTrue(optionalProduct.isPresent()),
                () -> assertFalse(optionalProduct.isEmpty()),
                () -> assertEquals("en dator", optionalProduct.get().getTitle())


        );
    }

    @Test
    void testfindAllCategories_givenDummylistOfCategorys_whenFindAllCategorys_thenCheckIfListIsnotDuplicatedAndcheckIfFindallCAtegorysizeis4() {

        //given

        // vår  skapade dummy list på categorys
        List<String> existingcategory = new ArrayList<>(Arrays.asList("electronics",
                "jewelery", "men's clothing", "women's clothing"
        ));



        //when
        // lista på category i databasen
        List<String> result = underTest.findAllCategories();


        //then
        // kollar om antalet category stämmer överens  med databasen som är 4
        assertEquals(4, underTest.findAllCategories().size());

        // jämnför vår dummy lista som vi har skapat med databasen om vi lägger till extra category
        //i vår dummy lista exempel en extra electronic eller jeewelry kommer den att blir fel
        assertEquals(existingcategory, result);


    }




    @Test
    void whenSerchingForNonExistingtilethen_ThrowException() {

        //when
        Optional<Product> optionalProduct = underTest.findByTitle("en title som inte finns");


        //then
        Assertions.assertAll(


                () -> assertFalse(optionalProduct.isPresent()),
                () -> assertTrue(optionalProduct.isEmpty()),
                () -> assertThrows(Exception.class, () -> optionalProduct.get())


        );


    }













}