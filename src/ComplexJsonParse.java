import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JsonPath js= new JsonPath(payload.CoursePrice());

        // Print # of courses returned by API
        int count= js.getInt("courses.size()");
        System.out.println(count);

        // Print purchase amount
        int totalAmount= js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // Print title of the first course
        String titleFirstCourse= js.get("courses[0].title");
        System.out.println(titleFirstCourse);

        // Print all course titles and their respective prices
        for (int i= 0; i < count; i++ ) {
            String title= js.get("courses[" + i + "].title");
            int price= js.get("courses[" + i + "].price");
            System.out.println(title + " " + price);
        }

        // Print # of copies sold by RPA Course
        for (int i= 0; i < count; i++ ) {
            String title= js.get("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")) {
                int copies= js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }

    }

}
