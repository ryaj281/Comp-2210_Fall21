import java.util.Arrays;
import java.util.Comparator;

/**
 * Autocomplete.
 */
public class Autocomplete {
    public Term[] termArray;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
	public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException();
      }
      termArray = terms;
      Arrays.sort(termArray);
    }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
	public Term[] allMatches(String prefix) {
	   if (prefix == null) {
         throw new NullPointerException();
      }
      int length = prefix.length();
      Comparator<Term> comp = Term.byPrefixOrder(length );
      Term key = new Term(prefix, -1);
      
      int first = BinarySearch.firstIndexOf(termArray, key, comp);
      int last = BinarySearch.lastIndexOf(termArray, key, comp);
      
      if (first == -1) {
         return null;
      }
      else {
         Term found[] = new Term[last - first + 1];
         for (int i = first; i <= last; i++) {
            found[i - first] = termArray[i];
         }
         Arrays.sort(found, Term.byDescendingWeightOrder());
         return found;
      }
      
    }

}

