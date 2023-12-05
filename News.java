import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class News {

    public static void main(String[] args) {
        
        // try catch block use to handle exception
        try {
            // create a file object
            File newsFile = new File("NewsSample.txt");
            // create a scanner object
            Scanner sc = new Scanner(newsFile);
            // define variable for counting the number of news
            int cnt = 0;

            // create an 2D arrayList for storing the news (title + url + date)
            ArrayList<ArrayList<String>> newsList = new ArrayList<ArrayList<String>>();

            while(sc.hasNextLine()) {

                // create a temporary ArrayList for storing news
                ArrayList<String> news = new ArrayList<>();

                // scan through each line of NewsSample.txt
                for(int i = 0; i< 4; i++) {
                    if (sc.hasNextLine()) {
                        news.add(sc.nextLine());  
                    }
                }

                String title = news.get(0);

                // add the news that contain "nature" to the 2D array
                if(title.toLowerCase().contains("nature")) {
                    newsList.add(news);
                }
            }

            // sort the news from latest to oldest
            sortNews(newsList);

            // display the news 
            System.out.println("Top 5 News about Nature");
            for(int i = 0; i < newsList.size(); i++) {
                cnt++;
                System.out.print("[" + cnt + "]");
                System.out.println(" " + newsList.get(i).get(0));
                System.out.println("    " + newsList.get(i).get(1));
                System.out.println("    " + newsList.get(i).get(2)); 
                System.out.println();             
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }

    // define a sorting method
    public static void sortNews(ArrayList<ArrayList<String>> arr) {

        // create an ArrayList for storing dates
        ArrayList<Date> datesList = new ArrayList<Date>();
        Date date;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");

        try {

            // parse the string into date and store it into ArrayList
            for(int i = 0; i < arr.size(); i++) {
            date = formatDate.parse(arr.get(i).get(2));
            datesList.add(date);
            }

            // Using buble sort to sort the news from latest to oldest
            for(int i = 0; i < arr.size(); i++) {
                for(int j = 0; j < arr.size() - i - 1; j++) {
                    if(datesList.get(j+1).after(datesList.get(j))) {
                        ArrayList<Date> datesListTemp = new ArrayList<Date>();
                        datesListTemp.add(datesList.get(j));
                        datesList.set(j, datesList.get(j+1));
                        datesList.set(j+1, datesListTemp.get(0));
                        ArrayList<ArrayList<String>> arrTemp = new ArrayList<ArrayList<String>>();
                        arrTemp.add(arr.get(j));
                        arr.set(j, arr.get(j+1));
                        arr.set(j+1,arrTemp.get(0));
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}