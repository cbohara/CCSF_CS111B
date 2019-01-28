declared variable = state variable type + name
int size;

must initialize a variable to use it
size = 0;

primitive types
- integers
- byte
- short 
- int
- long

real 
- float
- double

unicode character
- char

boolean
- boolean

object types
- defined in classes

strongly typed language
- every variable has a declared type
- declared type cannot change

object variables 
- have a declared type that cannot change
- also have an actual type that can change

constants
- holds the same value throughout its existance
final int MIN_HEIGHT = 60;

scope
- variables declared in block statement are local to that statement
- cannot be seen outside the brackets 

what is stored in memory
- primitive variables
	- store the actual value
	- store the actual data
- object variables/references
	- store reference/pointer/memory address where all info about the object resides

	Student s1 = new Student("Sue");
	Student s2 = new student("Bob");
	s2 = s1;
	//now both variables point to the same memory address 
	s1.setName("Sally");
	s1.getName(); //Sally
	s2.getName(); //Sally
	//any change to the object via one reference will change for all references

parameters in Java are passed by value
- formal parameter defined in method header
- actual parameters are values sent when the method is invoked
- when a method is invoked it's as if there is an assignment statement executed
	- formal parameter becomes an alias to the actual parameter
	- formalParam = actualParam
- if the actual parameter is a primitive
	- any changes made to the formal parameter in the method does not affect actual parameter

	public static main void(String args[]) {
		int num = 5;
		primParam(num);
		System.out.println(num); //5
	}
	public static void primParam(int number) {
		System.out.println(number); //5
		number = 99;
		System.out.println(number); //99
	}

- if the actual parameter is an object
	- any changes made to formal parameter within the method affects object outside the method

	public static main void(String args[]) {
		Point p = new Point(0,0);
		objParam(p);
		System.out.println(p); //(99,99)
	}
	public static void objParam(Point point) {
		System.out.println(point); //(0,0)
		point.setLocation(99, 99);
		System.out.println(point); //(99,99)
	}

static
- class methods
	- most methods execute in reponse to method calls on specific objects
	- class methods do not depend on a specific object
	- generally act as helper methods
	- ex: Math.sqrt(900.0); 
	- you do not need to make a Math object in order to use its static sqrt method
- class variables
	- typically objects contain their own individual instance variables 
	- class variables = all objects created from that class share copy of a single variable
- class constant
	- ex: Math.PI
	- defined as public final static Math.PI = 3.14...
	- public = can use in your own class
	- final = cannot change
	- static = allows you to access via class name and dot separator like a class method

new 
- allocates necessary memory for an object
- executes the constructor
- returns the address of (reference to) the object to store in variable

constructors
- every class must have a constructor
	- if not specified in code, compiler creates default constructor
	- default constructor initializes instance variables to defaults if not specified
		- numbers = 0.0, boolean = false, objects = null
	- if a constructor is specified, then no default constructor is created
		- if you want to instaniate an object with no arguments, need to write explicitly

- overloaded constructors
	public class Parcel {
		private String id;
		private double weight;

		public Parcel(String id, double weight) {
			if (!id.equals("") {
				this.id = id;
			}
			if (weight > 0.0) {
				this.weight = weight;
			}
		}

		public Parcel(String id) {
			// invokes constructor that requires 2 parameters
			this(id, 0.0);
		}

		public Parcel(double weight) {
			// invokes constructor that requires 2 parameters
			this("ABC123", weight);
		}

		public Parcel(Parcel sameParcel) {
			// invokes constructor that requires 2 parameters
			this(sameParcel.getId(), sameParcel.getWeight());
		}
	}

- constructors are not inherited even though they have public visibility
- child class needs its own constructor
- best practice = call parent constructor first then do any additional setup for child after

	public class OvernightParcel extends Parcel {
		private boolean signatureRequired;
		
		public OvernightParcel(String id, double weight, boolean signatureRequired) {
			// super executes parent constructor
			super(id, weight);
			this.signatureRequired = signatureRequired;
		}

super 
- invoke parent constructor
- invoke parent's version of overridden method

override 
- child class can override the definition of an inherited method
- must have the same method name and parameters 
	- use @override annotation to ensure method header is correct
- can provide its totally unique implementation or 
- extend parents implementation
	- use super.method() to invoke parents implementation within the overridden method

equals
- must overwrite object version of equals in order to leverage in other methods
- ex: sorting an array of objects
	@override
	public boolean equals(Object obj) {
		if(obj instanceof MyClass) {
			MyClass otherObj = (MyClass) obj;
			// compare the objects
		} else {
			return false;
		}
	}

polymorphism
- typically we invoke a method through an object
	myObject.doSomething();
- the invocation is bound to the definition of the method
	public void doSomething() 
- if binding occured at compile time then that line of code would call the same method every time
- however java defers binding until run time = dynamic binding
- different methods can be called by the same line of code

- declared typed
	- determined at compile time
		- controls which methods can be invoked 
		- if a method doesn't exist for the declared object then compile error
	- you can declare an object high on inheritance heirarchy
- actual type
	- can instantiate the object lower in inheritance heirarchy
	- determined at run time
	- JVM uses actual type to determine which method version to invoke

downcast 
- get method that exists for specific actual type
	for(int i = 0; i < empList.length; i++) {
		Employee e = empList[i];
		e.pay();
		if(e instanceof SalaryEmployee) {
			// downcast Employee to SalaryEmployee
			SalaryEmployee s = (SalaryEmployee) e;
			s.vacation();
		}
	}

generic type
- allows Object type information to vary
	- cannot use primitives
- puts a compile time check on type safety

- class ArrayList<E>
	- <E> stands for a flexible element type
	- you can create an ArrayList holding strings or ints or whatever
	- not limited to only a single type

- ex: declare an ArrayList will hold Strings
	ArrayList<String> wordList = new ArrayList<String>();
	//or 
	ArrayList<String> wordList = new ArrayList<>();

- ex: declare an ArrayList that will hold integers
	// cannot declare using primitive integer
	ArrayList<int> numberList = new ArrayList<int>();
	// must use Integer wrapper class
	ArrayList<Integer> numberList = new ArrayList<Integer>();
	// can add primitive integers to the list
	// primitives are automatically converted to Integers via autoboxing
	int num = 5;
	numberList.add(num);

rules
- cannot use primitives
- instanceof only checks the raw type
	- if(obj instanceof Box<Integer>) // not allowed - checking generic type
	- if(obj instanceof Box<?>) // allowed! checking wildcard
- cannot use generics with Arrays
	- use ArrayList instead
- cannot instantiate type varibles 
	- first = new T(); // not allowed
