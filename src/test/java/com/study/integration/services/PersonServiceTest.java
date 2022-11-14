package com.study.integration.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.study.integration.entities.Person;
import com.study.integration.repositories.PersonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations={"classpath:application.properties"})
public class PersonServiceTest {

	//Creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
	@InjectMocks
    private PersonService service;

	//Creates a mock(imitation) implementation for the classes you need.
    @Mock
    private PersonRepository repository;
    
    private Person createPerson() {
		return new Person("Person tst", "person@tst.com");
	}
    
    @Test
    void should_create_person() throws Exception {
        // Arrange
        final Person personToSave = createPerson();
        when(repository.save(any(Person.class))).thenReturn(personToSave);

        // Act
        final Person actual = service.save(personToSave);

        // Assert
        //RecursiveComparison: field by field comparison
        assertThat(actual).usingRecursiveComparison().isEqualTo(personToSave);
        verify(repository, times(1)).save(any(Person.class));
        verifyNoMoreInteractions(repository);
    }
    
    @Test
    void should_find_person_byId() {
        // Arrange
        final Person expectedPerson = createPerson();
        when(repository.findById(1L)).thenReturn(Optional.of(expectedPerson));

        // Act
        final Optional<Person> actual = service.findById(1L);

        // Assert
        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expectedPerson);
        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_find_all_persons() {
        // Arrange
    	List<Person> persons = new ArrayList<>();
		persons.add(createPerson());
		persons.add(createPerson());
        when(repository.findAll()).thenReturn(persons);

        // Act & Assert
        assertThat(service.findAll()).hasSize(2);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }
}
