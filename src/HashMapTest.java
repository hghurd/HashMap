import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.fail;

public class HashMapTest {

	public boolean contains ( List< SimpleMap.Entry > list, String key ) {
		for( SimpleMap.Entry entry : list ) {
			if ( entry.getKey ().equals ( key ) ) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void inheritanceTest( ) {
		Object hmap = new HashMap ( 100 );
		if ( ! ( hmap instanceof SimpleMap ) ) {
			fail( "Your HashMap class does not extend the provided abstract class." );
		}
	}

	@Test
	public void capacityTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		int capacity = hmap.capacity ();
		if ( capacity != 100 ) {
			fail( String.format("new HashMap ( 100 ).capacity() = %d expected 100", capacity));
		}
	}

	@Test
	public void capacityTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		List< SimpleMap.Entry >[ ] hashArray = hmap.hashArray ( );
		int capacity = hashArray.length;
		if ( capacity != 100 ) {
			fail( String.format("hashArray().length = %d expected capacity = 100", capacity));
		}
	}

	@Test
	public void putTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		String key = "Thing";
		int value = 1;
		try {
			hmap.put(key, value);
		} catch ( IndexOutOfBoundsException e ) {
			String [ ] parts = e.getMessage().split(",");
			Scanner iscan = new Scanner( parts[0] );
			iscan.next( );
			int index = iscan.nextInt();
			Scanner sscan = new Scanner( parts[1] );
			sscan.next( );
			int size = sscan.nextInt();
			if ( index == size ) {
				Error err =  new AssertionError("Index Out Of Bounds. " + e.getMessage() + "\nCheck your loop end conditions.\nYou may have an Off-By-One issue in a loop.");
				err.setStackTrace( e.getStackTrace() );
				throw err;
			} else {
				throw e;
			}
		}
	}

	@Test
	public void putTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put("A", 0);
		hmap.put("B", 0);
		hmap.put("C", 0);
		hmap.put("C", 3);
		String key = "C";
		int value = 3;
		boolean found = false;
		boolean correct = false;
		for( HashMap.Entry kvp : hmap.entryList ( ) ) {
			if ( key.equals(kvp.getKey()) ) {
				found = true;
				if ( value == kvp.getValue() ) {
					correct = true;
				}
			}
		}
		if ( !correct ) {
			fail( String.format("put(%s, %d) executed to change value from 0 to 3, but value found was unchanged: %s.", key, value, hmap.entryList ()));
		}
	}

	@Test
	public void putTest3( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put("A", 1);
		hmap.put("B", 2);
		hmap.put("C", 3);
		String key = "C";
		int value = 3;
		boolean found = false;
		boolean correct = false;
		for( HashMap.Entry kvp : hmap.entryList ( ) ) {
			if ( key.equals(kvp.getKey()) ) {
				found = true;
				if ( value == kvp.getValue() ) {
					correct = true;
				}
			}
		}
		if ( !found ) {
			fail( String.format("put(%s, %d) executed, but key was not found in associated list: %s.", key, value, hmap.entryList ()));
		}
		if ( !correct ) {
			fail( String.format("put(%s, %d) executed, but value found was incorrect: %s.", key, value, hmap.entryList ()));
		}
	}

	@Test
	public void putTest4( ) {
		HashMap hmap = new HashMap ( 100 );
		try {
			hmap.put ( "Clark", 0 );
			hmap.put ( "Carol", 1 );
			hmap.put ( "Christian", 2 );
			hmap.put ( "Codee", 3 );
		} catch ( Exception e ) {

		}
		boolean result = ( 0 == (int) hmap.get ( "Clark" )) && ( 1 == (int) hmap.get ( "Carol" )) && ( 2 == (int) hmap.get ( "Christian" )) && ( 3 == (int) hmap.get ( "Codee" ));
		if ( !result ) {
			fail( String.format("put appears to be encountering collisions %s", hmap.entryList ()));
		}
	}

	@Test
	public void putTest5( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "Clark", 0 );
		hmap.put ( "Carol", 1 );
		hmap.put ( "Christian", 2 );
		hmap.put ( "Codee", 3 );
		if ( hmap.size ( ) != 4 ) {
			fail ( String.format ( "put 4 items, size=%d %s", hmap.size (),
			                       Arrays.toString ( hmap.hashArray () ) ) );
		}
	}

	@Test
	public void putTest6( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "APPLE", 0 );
		hmap.put ( "BAKER", 1 );
		hmap.put ( "CHARLIE", 2 );
		hmap.put ( "DOG", 3 );
		List< SimpleMap.Entry>[] hashArray = hmap.hashArray ( );
		if ( !contains( hashArray[0], "DOG" ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; DOG not found at index=0 hashArray=%s", Arrays.toString( hashArray ) ) );
		}
		if ( !contains( hashArray[1], "BAKER" ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; BAKER not found at index=1 hashArray=%s", Arrays.toString( hashArray ) ) );
		}
		if ( !contains( hashArray[2], "APPLE" ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; APPLE not found at index=2 hashArray=%s", Arrays.toString( hashArray ) ) );
		}
		if ( !contains( hashArray[2], "CHARLIE" ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; CHARLIE not found at index=2 hashArray=%s", Arrays.toString( hashArray ) ) );
		}
		if ( !hashArray[3].isEmpty () ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; expected index=3 to be empty. hashArray=%s", Arrays.toString( hashArray ) ) );
		}
	}



	@Test
	public void getTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		try {
			hmap.put("A", 1);
		} catch ( Exception e ) {
			// Left Empty
		}
		if ( hmap.entryList ().size() == 0 ) {
			fail( String.format( "Can't complete get test because put(%s, %d) failed, array list is empty.", "A", 1) );
		}
	}

	@Test
	public void getTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put("A", 1);
		String key = "A";
		Integer value = 1;
		Integer result = hmap.get(key);
		if (!value.equals(result)) {
			boolean found = false;
			boolean correct = false;
			for ( HashMap.Entry kvp : hmap.entryList ( )) {
				if (key.equals(kvp.getKey())) {
					found = true;
					if (value.equals( kvp.getValue()) ) {
						correct = true;
					}
				}
			}
			if (!found) {
				fail(String.format("get(%s) returned %d when I was expecting %d. The key was not found in associated list: %s.", key, result, value, hmap.entryList ()));
			}
			if (!correct) {
				fail(String.format("get(%s) returned %d when I was expecting %d. The value was wrong in the list: %s.", key, result, value, hmap.entryList ()));
			}
			fail(String.format("get(%s) returned %d when I was expecting %d. However, the correct value is in the list: %s.", key, result, value, hmap.entryList ()));
		}
	}

	@Test
	public void getTest3( ) {
		HashMap hmap = new HashMap ( 100 );
		for( char c = 'A'; c < 'K'; c++ ) {
			hmap.put( "" + c, (int) c );
		}
		boolean allnull = true;
		for( char c = 'A'; c < 'Z'; c++ ) {
			if ( hmap.get( "" + c ) != null ) {
				allnull = false;
			}
		}
		if ( allnull ) {
			fail( String.format( "The get method always seems to return null. Trace through the method to see if any other result is possible." ) );
		}
	}

	@Test
	public void getTest4( ) {
		HashMap hmap = new HashMap ( 100 );
		for( char c = 'A'; c < 'K'; c++ ) {
			hmap.put( "" + c, (int) c );
		}
		for( char c = 'A'; c < 'K'; c++ ) {
			Integer result = hmap.get( "" + c );
			if ( result == null ) {
				fail( String.format ( "get('%s') returned null expected %d\n%s", ""+c, (int) c, hmap.entryList () ));
			}
			if ( (int) result != (int) c ) {
				fail( String.format( "get('%s') == %d expected %d" , ""+c, result, (int) c  ));
			}
		}
	}

	@Test
	public void getTest5( ) {
		HashMap hmap = new HashMap ( 100 );
		for( char c = 'A'; c < 'z'; c++ ) {
			hmap.put( "" + c, (int) c );
		}
		for( char c = 'A'; c < 'z'; c++ ) {
			Integer result = hmap.get( "" + c );
			if ( result == null ) {
				fail( String.format ( "get('%s') returned null expected %d\n%s", ""+c, (int) c, hmap.entryList () ));
			}
			if ( (int) result != (int) c ) {
				fail( String.format( "get('%s') == %d expected %d" , ""+c, result, (int) c  ));
			}
		}
	}

	@Test
	public void getTest6( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "APPLE", 0 );
		hmap.put ( "BAKER", 1 );
		hmap.put ( "CHARLIE", 2 );
		hmap.put ( "DOG", 3 );
		Integer result = hmap.get ( "APPLE" );
		if ( result == null || !result.equals ( 0 ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; get(APPLE)=%d expected 0", result ) );
		}
		result = hmap.get ( "BAKER" );
		if ( result == null || !result.equals ( 1 ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; get(BAKER)=%d expected 1", result ) );
		}
		result = hmap.get ( "CHARLIE" );
		if ( result == null || !result.equals ( 2 ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; get(CHARLIE)=%d expected 2", result ) );
		}
		result = hmap.get ( "DOG" );
		if ( result == null || !result.equals ( 3 ) ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; get(DOG)=%d expected 3", result ) );
		}
	}

	@Test
	public void isEmptyTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		if ( !hmap.isEmpty () ) {
			fail( String.format( "Immediately after HashMap instanciated by calling the no-args constructor, isEmpty returned false." ) );
		}
	}

	@Test
	public void isEmptyTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put( "A", 1 );
		if ( hmap.isEmpty () ) {
			fail( String.format( "Immediately after put( \"A\", 1 ), isEmpty returned true." ) );
		}
	}

	@Test
	public void isEmptyTest3( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put( "A", 1 );
		hmap.remove ( "A" );
		if ( !hmap.isEmpty () ) {
			fail( String.format( "After removing last association, isEmpty returned false." ) );
		}
	}

	@Test
	public void isEmptyTest4( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put( "A", 1 );
		hmap.put( "B", 2 );
		hmap.put( "C", 3 );
		hmap.remove ( "B" );
		hmap.remove ( "C" );
		hmap.remove ( "A" );
		if ( !hmap.isEmpty () ) {
			fail( String.format( "After removing last association, isEmpty returned false." ) );
		}
	}

	@Test
	public void sizeTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		int exprected = 0;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after HashMap instanciated by calling the no-args constructor, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		int exprected = 1;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after put( \"A\", 1 ), size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest3( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		int exprected = 3;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after adding three items to the map, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest4( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		hmap.put ( "C", 2 );
		hmap.put ( "C", 1 );
		int exprected = 3;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three items to the map then replaced the value in an entry, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest5( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		hmap.remove ( "B" );
		int exprected = 2;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three items to the map then removed an entry, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest6( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "Larry", 1 );
		hmap.put ( "Curly", 2 );
		hmap.put ( "Moe", 3 );
		int exprected = 3;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three items to the map, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest7( ) {
		HashMap hmap = new HashMap ( 2 );
		hmap.put ( "Larry", 1 );
		hmap.put ( "Curly", 2 );
		hmap.put ( "Moe", 3 );
		int exprected = 3;
		int size = hmap.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three items to the map, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void removeTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		Integer exprected = null;
		Integer result = hmap.remove ( "A" );
		if ( exprected != result ) {
			fail( String.format( "Executed remove ( \"A\" ) on empty list, remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void removeTest2( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		Integer exprected = 1;
		Integer result = hmap.remove ( "A" );
		if ( !exprected.equals( result ) ) {
			fail( String.format( "Executed put ( \"A\", 1 ) then remove ( \"A\" ), remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void removeTest3( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		hmap.remove ( "B" );
		Integer exprected = null;
		Integer result = hmap.remove ( "B" );
		if ( exprected!= result ) {
			fail( String.format( "Executed put ( \"B\", 2 ) then remove ( \"B\" ) then remove ( \"B\" ), remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void removeTest4( ) {
		HashMap hmap = new HashMap ( 100 );
		for( int i = 0; i < 255; i += 26 ) {
			hmap.put ( "" + (char) i, i );
		}
		Integer exprected = 26;
		Integer result = hmap.remove ( "" + (char) 26 );
		if ( exprected!= result ) {
			fail( String.format( "Executed put ( \"%s\", 26 ) then remove ( \"%s\" ) then remove ( \"B\" ), returned %d expected 26.", (char) 26, (char) 26, result ) );
		}
	}

	@Test
	public void entryListTest1( ) {
		HashMap hmap = new HashMap ( 100 );
		hmap.put ( "A", 1 );
		hmap.put ( "B", 2 );
		hmap.put ( "C", 3 );
		List< SimpleMap.Entry > list = hmap.entryList ( );
		if ( list.size( ) != 3 ) {
			fail( String.format( "Added three hashArray to list then entryList( ) returned %s.", list ) );
		}
		boolean a = false, b = false, c = false;
		for ( SimpleMap.Entry entry : list ) {
			a = a || entry.getKey ().equals ( "A" ) && entry.getValue () == 1;
			b = b || entry.getKey ().equals ( "B" ) && entry.getValue () == 2;
			c = c || entry.getKey ().equals ( "C" ) && entry.getValue () == 3;
		}
		if ( (!a) || (!b) || (!c) ) {
			fail( String.format( "Added three hashArray to list then entryList( ) returned %s.", list ) );
		}
	}

	@Test
	public void loadFactor1( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "APPLE", 0 );
		hmap.put ( "BAKER", 1 );
		hmap.put ( "CHARLIE", 2 );
		hmap.put ( "DOG", 3 );
		double result = hmap.loadFactor ();
		if ( result != 1.0 ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; loadFactor=%f expected 1.0", result ) );
		}
	}

	@Test
	public void loadFactor2( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "Larry", 0 );
		hmap.put ( "Curly", 1 );
		hmap.put ( "Moe", 2 );
		double result = hmap.loadFactor ();
		if ( result != 0.75 ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; loadFactor=%f expected 0.75", result ) );
		}
	}

	@Test
	public void loadVariance1( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "APPLE", 0 );
		hmap.put ( "BAKER", 1 );
		hmap.put ( "CHARLIE", 2 );
		hmap.put ( "DOG", 3 );
		int result = hmap.loadVariance ();
		if ( result != 2 ){
			fail( String.format ( "new HashMap(4); keys: APPLE, BAKER, CHARLIE, DOG; loadVariance=%d expected 2", result ) );
		}
	}

	@Test
	public void loadVariance2( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "Larry", 0 );
		hmap.put ( "Curly", 1 );
		hmap.put ( "Moe", 2 );
		int result = hmap.loadVariance ( );
		if ( result != 2 ) {
			fail ( String.format (
					  "new HashMap(4); keys: Larry, Curly, Moe; loadVariance=%d expected 2",
					  result ) );
		}
	}

	@Test
	public void loadVariance3( ) {
		HashMap hmap = new HashMap ( 4 );
		hmap.put ( "Larry", 0 );
		hmap.put ( "Curly", 1 );
		hmap.put ( "Moe", 2 );
		hmap.put ( "Shemp", 3 );
		hmap.put ( "Joe", 4 );
		hmap.put ( "Curly Joe", 5 );
		int result = hmap.loadVariance ( );
		if ( result != 1 ) {
			fail ( String.format (
					  "new HashMap(4); keys: Larry, Curly, Moe, Shemp, Joe, curly Joe; loadVariance=%d expected 1",
					  result ) );
		}
	}

}