import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {
      private String query = null;
      private long weight = 0;
      
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
     
    public Term(String query, long weight) {
         if (query == null) {
            throw new NullPointerException();
         }
         if (weight < 0) {
            throw new IllegalArgumentException();
         }
         this.query = query;
         this.weight = weight;

    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
      return new Comparator<Term>() {
         @Override
         public int compare(Term t1, Term t2) {
            if (t1.weight < t2.weight) {
               return 1;
            } else if (t1.weight > t2.weight) {
               return -1;
            } else {
               return 0;
            } 
         }  
      };
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
         if (length <= 0) {
            throw new IllegalArgumentException();
         }
         return new Comparator<Term>() {
         
            @Override
            public int compare(Term t1, Term t2) {
               if (t1.query.length() < length || t2.query.length() < length) {
                  return t1.compareTo(t2);
               }
            return t1.query.substring(0, length). compareTo(t2.query.substring(0, length));
            }
         };
   }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
         int cmp = this.query.compareTo(other.query);
         return cmp;
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
         return query + "\t" + weight;
    }

}
