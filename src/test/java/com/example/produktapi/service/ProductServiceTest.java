package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) //sätter upp miljön för mocking

class ProductServiceTest {
    @Captor
    ArgumentCaptor<Product> productCaptor;
    @Mock //det som ska mockas
    private ProductRepository repository;

    @InjectMocks // det som är beroende utav mockningen
    private ProductService underTest;



    Integer id = 2;


    @Test
    void WhenGetALLProducts_ThenExactlyOneInteractionWhitRepositoryMethodFindall() {
        // when
        underTest.getAllProducts();

        //then

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

        // verify(repository,times(2)).deleteAll(); // will give eror due to deletall is not fucntion in this

    }

    @Test
    void WhenGetALLCaterg0rys_ThenExactlyOneInteractionWhitRepositoryMethodFindall() {

        //when
        underTest.getAllCategories();
        //then
        verify(repository, times(1)).findAllCategories();

        verifyNoMoreInteractions(repository);
    }

    @Test
    void givenAnExistingCategorywhenGetProductsByCategory_thenReciveANonEmptylist() {


        //given
        String category = "pokemon";
        Product product = new Product("pikachu", 200.44, category, "electric", "bild");


        given(repository.findByCategory(category)).willReturn(List.of(product));


        // When När metoden getProductsByCategory anropas med kategorin "pokemon" och produkten skickas tillbaka.
        List<Product> result = underTest.getProductsByCategory(category);


        // Then Kontrollerar att titeln är samma, att det finns en produkt i listan och att beskrivningen är korrekt.
        assertEquals("pikachu", result.get(0).getTitle()); //Kollar så det är samma title
        assertEquals(1, result.size()); // Förväntar sig en produkt i den kategorin
        assertEquals("electric", result.get(0).getDescription()); // Hämtar även ut beskrivningen för att dubbelkolla


    }


    @Test
    @DisplayName("normal flöde")
    void givenAnExistingId_whenGetProdcutByID_ThenReciveProductById() {

        //given  have a global id that is set at top of the page

        Product product = new Product("", 45.00, "", "", "");

        product.setId(id);



        given(repository.findById(product.getId())).willReturn(Optional.of(product));

        //when
        underTest.addProduct(product);



        //then


        Assertions.assertTrue(repository.findById(id).isPresent());


    }


    @Test


    void testGetProductByIDwhen_NonExistingIdIsGiven_thenThrowEntityNotFoundException() {

        //when
        when(repository.findById(id)).thenReturn(Optional.empty());

        //then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
                    System.out.println(id);

                    underTest.getProductById(id);
                }
        );


        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());

    }

    @Test
    @DisplayName("Add-metod normal-flöde")
    void whenAddingAProduct_thensaveMethodShouldbeCalled() {
        //given

        Product product = new Product("dator rätt object som sparas", 4000.0, "", "", "");


        //when

        underTest.addProduct(product);

        //then
        verify(repository).save(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }

    @Test
    @DisplayName("Add metod felflöde")
    void givenAnExisting_TitlewhenAddingProductsWithDuplicatedTitle_thenThrowBadExceptionError() {

        //given
        String title = "vår test-title";
        Product product = new Product(title, 34.0, "", "", "");
        given(repository.findByTitle(title)).willReturn(Optional.of(product));


        //then


        BadRequestException exception = assertThrows(BadRequestException.class,
                //when
                () -> underTest.addProduct(product));
        verify(repository, times(1)).findByTitle(title);
        verify(repository, never()).save(any());
        assertEquals("En produkt med titeln: vår test-title finns redan", exception.getMessage());
    }

    @Test
    void givenTwoProduct_whenFindByID_thenVerifyNewProdut() {

        Integer id = 4;

        //given


        Product product = new Product("dator rätt object som sparas",
                4000.0, "electronic", " computer", "");

        product.setId(id);
        System.out.println(product);

        Product newproduct = new Product("ny data", 45.0, "electronic", "pc", "");




//when
        when(repository.findById(id)).thenReturn(Optional.of(product));
        underTest.updateProduct(newproduct,product.getId());



        //then

        verify(repository,times(1)).findById(product.getId());
        verify(repository,times(1)).save(newproduct);
        verifyNoMoreInteractions(repository);



    }

    @Test
    void givenAnIdifMatched_IfIdIsNotValid_thenThrowEntityNotFoudnException () {

        Integer id = 4;

        Product updateproduct = new Product("", 45.0, "", "", "");


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {


                    underTest.updateProduct(updateproduct, id);
                }
        );


        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());


    }


    @Test
    void givenValidID_whenFindByID_thenInteratcionWhitDeleteByIDEcaxtlyOneTime() {


        //given
        Integer id = 4;


        String category = "pokemon";

        Product product = new Product("pikachu", 200.44, category, "electric", "bild");

        product.setId(id);


        //when
        when(repository.findById(id)).thenReturn(Optional.of(product));


        underTest.deleteProduct(id);


        //then

        verify(repository, times(1)).deleteById(id);
        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository);

    }


    @Test
    void whengetProductbyIdIFifProductbyidIsNotFound_thenThrowEntitynotfoundsException() {

        Integer id = 4;


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {


                    underTest.deleteProduct(id);
                }
        );


        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());


    }


    @Test
    void testingGitHubActions(){


        assertEquals(3,3);
    }

}