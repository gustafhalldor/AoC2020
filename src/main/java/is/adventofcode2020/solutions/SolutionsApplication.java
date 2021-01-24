package is.adventofcode2020.solutions;

import is.adventofcode2020.solutions.dec22.Dec22;
import is.adventofcode2020.solutions.dec23.Dec23;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolutionsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SolutionsApplication.class, args);
		long start = System.currentTimeMillis();
		//System.out.println(Dec01.solve());
		//System.out.println(Dec02.solve());
		//System.out.println(Dec03.solve());
		//System.out.println(Dec04.solve());
		//System.out.println(Dec05alt.solve());
		//System.out.println(Dec06.solve());
		//System.out.println(Dec07.solve());
		//System.out.println(Dec08.solve());
		//System.out.println(Dec09.solve());
		//System.out.println(Dec10.solve());
		//System.out.println(Dec11.solve());
		//System.out.println(Dec12.solve());
		//System.out.println(Dec13.solve());
		//System.out.println(Dec14.solve());
		//System.out.println(Dec15.solve());
		//System.out.println(Dec16.solve());
		//System.out.println(Dec18.solve());
		//System.out.println(Dec19.solve());
		//System.out.println(Dec20.solve());
		//System.out.println(Dec20.solve());
		//System.out.println(Dec21.solve());
		System.out.println(Dec22.solve());
		//System.out.println(Dec23.solve());
		//System.out.println(Dec24.solve());
		//System.out.println(Dec25.solve());
		long end = System.currentTimeMillis();
		System.out.println("time in milliseconds: " + (end - start));
	}

}
