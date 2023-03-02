package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {
    @Captor
    ArgumentCaptor<Product> productCaptor;
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService underTest;

    Integer id = 2;


    @Test
    void WhenGetALLProducts_ThenExactlyOneInteractionWhitRepositoryMehtodFindall() {
        // when
        underTest.getAllProducts();

        //then

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

        // verify(repository,times(2)).deleteAll(); // will give eror due to deletall is not fucntion in this

    }

    @Test
    void WhenGetALLCaterg0rys_ThenExactlyOneInteractionWhitRepositoryMehtodFindall() {

        //when
        underTest.getAllCategories();
        //then
        verify(repository, times(1)).findAllCategories();

        verifyNoMoreInteractions(repository);
    }

    @Test
    void testgetProductsByCategory_givenAnExistingCategory_whenthenReciveANonEmptylist() {


        //given
        String category = "pokemon";
        Product product = new Product("pikachu", 200.44, category, "electric", "bild");


        given(repository.findByCategory(category)).willReturn(List.of(product));


        // When När metoden getProductsByCategory anropas med kategorin "hallp" och produkten skickas tillbaka.
        List<Product> result = underTest.getProductsByCategory(category);


        // Then Kontrollerar att titeln är samma, att det finns en produkt i listan och att beskrivningen är korrekt.
        assertEquals("pikachu", result.get(0).getTitle()); //Kollar så det är samma title
        assertEquals(1, result.size()); // Förväntar sig en produkt i den kategorin
        assertEquals("electric", result.get(0).getDescription()); // Hämtar även ut beskrivningen för att dubbelkolla


    }


    @Test
    void getproductbyid() {

//given

        Product product = new Product("", 45.00, "", "", "");

        product.setId(id);

        underTest.addProduct(product);


//then


        given(repository.findById(product.getId())).willReturn(Optional.of(product));
        Assertions.assertTrue(repository.findById(product.getId()).isPresent());


    }


    @Test
    void testGetProductByIDWhenNonExistingIdIsGiven_ThenThrowEntityNotFoundException() {

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
    void whenAddingAProduct_thensaveMethodShouldbeCalled() {
        //given

        Product product = new Product("dator rätt object som sparas", 4000.0, "", "", "");

        //Product product2=new Product("dator som är fel object",4000.0,"","","");
        //when

        underTest.addProduct(product);

        //then
        verify(repository).save(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }

    @Test
    void WhenaddingProductsWithDuplicateTitle_thenThrowError() {

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
    void testUdateProduct_WhenFindById_thenUpdateProduct() {

        Integer id = 4;

        //given

        Product product = new Product("dator rätt object som sparas",
                4000.0, "electronic", " computer", "");

        product.setId(id);
        System.out.println(product);

        Product updatedproduct = new Product("", 45.0, "", "", "");

        updatedproduct.setId(id);
        updatedproduct.setTitle("charmander");
        updatedproduct.setDescription("fire-type");
        updatedproduct.setCategory("pokemon");
        System.out.println(updatedproduct);

//when
        when(repository.findById(id)).thenReturn(Optional.of(updatedproduct));
        when(repository.save(updatedproduct)).thenReturn(updatedproduct);


        Product result = underTest.updateProduct(updatedproduct, id);

        //then

        assertEquals("charmander", result.getTitle());//checking if updated tile is charmander
        assertEquals("fire-type", result.getDescription());// check if  result of decsiption is fire-type
        assertEquals("pokemon", result.getCategory()); // chck if result of category is pokemon


    }

    @Test
    void testUdateProduct_WhenFindById_() {

        Integer id = 4;

        Product updateproduct = new Product("", 45.0, "", "", "");


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {


                    underTest.updateProduct(updateproduct, id);
                }
        );


        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());


    }


    @Test
    void testDeleteProduct_whengetprodcutbyid_IfidExist_thendeleteproduct() {


        //given
        Integer id = 4;


        String category = "pokemon";

        Product product = new Product("pikachu", 200.44, category, "electric", "bild");
        product.setId(id);
        System.out.println(product);

        //when
        when(repository.findById(id)).thenReturn(Optional.of(product));


        underTest.deleteProduct(id);

        System.out.println(underTest);

        verify(repository, times(1)).deleteById(id);


//then

        assertNotNull(underTest.getProductById(id));


        underTest.deleteProduct(id);

    }


    @Test
    void testDeleteProduct_whengetProductbyid_ifProductbyidIsNotFound_thenThrowEntitynotfounds() {

        Integer id = 4;


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {


                    underTest.deleteProduct(id);
                }
        );


        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());


    }


}