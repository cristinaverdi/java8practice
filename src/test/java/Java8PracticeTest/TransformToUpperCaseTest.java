package Java8PracticeTest;

import com.cristinaverdi.streamspractice.Transform;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformToUpperCaseTest {

    private Transform transform;
    private List<String> collection;

    @Before
    public void set_up() {
        transform = new Transform();
        collection = asList("Hola", "me", "llamo", "erl");
    }

    //map
    @Test public void
    transform_elements_of_a_collection_to_uppercase() {
        List<String> expectedUpperCaseCollection = asList("HOLA", "ME", "LLAMO", "ERL");

        final List<String> upperCaseCollection = transform.toUpperCase(collection);

        assertThat(upperCaseCollection).hasSameElementsAs(expectedUpperCaseCollection);
    }

    //filter by condition
    @Test public void
    filter_collection_so_that_only_elements_with_less_than_4_characters_are_returned() {
        List<String> expectedFilteredCollection = asList("me", "erl");

        List<String> filteredCollection = transform.filterElementsWithLessThanFourCharacters(collection);

        assertThat(filteredCollection).hasSameElementsAs(expectedFilteredCollection);
    }

    //flatten a multidimensional matrix
    @Test public void
    flatten_multidimensional_collection() {
        List<List<String>> matrix = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expectedFlattenedNames = asList("Viktor", "Farcic", "John", "Doe", "Third");

        List<String > flattenedNames = transform.flattenMatrix(matrix);

        assertThat(flattenedNames).hasSameElementsAs(expectedFlattenedNames);
    }

    //findMax
    @Test public void
    getOldestPerson() {
        Person expectedOldestPerson = new Person("Ana", 101);
        List<Person> people = asList(new Person("Eva", 3), new Person("Marc", 100), new Person("Ana", 101));

        Person oldestPerson = transform.getOldestPerson(people);

        assertThat(oldestPerson).isEqualToComparingFieldByField(expectedOldestPerson);
    }

    //statistics

    //reduce
    @Test public void
    calculate_total() {
        int total = transform.total(asList(1, 2, 3, 4, 5));
        assertThat(total).isEqualTo(1+2+3+4+5);
    }

    //filter by condition and map
    @Test public void
    find_person_over_18() {
        List<Person> people = asList(
                new Person("Sara", 22 ),
                new Person("Ana", 19),
                new Person("Viktor", 2),
                new Person("Eva", 17)
                );

        List<String> peopleOver18 = transform.findPeopleOver18(people);
        assertThat(peopleOver18)
                .contains("Sara", "Ana")
                .doesNotContain("Viktor", "Eva");

    }

    //partition
    @Test public void
    separate_adults_from_kids() {
        final Person ana = new Person("Ana", 3);
        final Person eva = new Person("Eva", 18);
        final Person viktor = new Person("Viktor", 43);
        List<Person> people = asList(ana, eva, viktor);

        Map<Boolean, List<Person>> taggedPeople= transform.separateAdultsFromKids(people);

        final List<Person> adults = taggedPeople.get(true);
        assertThat(adults).hasSameElementsAs(asList(viktor, eva));

        final List<Person> kids = taggedPeople.get(false);
        assertThat(kids).hasSameElementsAs(asList(ana));
    }

    //groupBy
    @Test public void
    groupByName() {
        final Person sara = new Person("Sara", 18);
        final Person sara2 = new Person("Sara", 2);
        final Person viktor = new Person("Viktor", 43);

        List<Person> people = asList(sara, sara2, viktor);
        Map<String, List<Person>> result = transform.groupByName(people);

        assertThat(result.get("Sara")).contains(sara2, sara);
        assertThat(result.get("Viktor")).contains(viktor);
    }

    //join
    @Test public void
    get_people_names() {
        final Person sara = new Person("Sara", 18);
        final Person sara2 = new Person("Sara", 2);
        final Person viktor = new Person("Viktor", 43);

        List<Person> people = asList(sara, sara2, viktor);

        assertThat(transform.getPeopleNames(people)).isEqualTo("Names: Sara, Sara, Viktor.");
    }
}
