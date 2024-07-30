import java.io.*;
import java.util.*;
public class MapLoader {
    public static Obstacle[] mapObjs(File mapFile){
        try {
            ArrayList<Obstacle> obstaclesArrayList = new ArrayList<Obstacle>();
            Scanner reader = new Scanner(mapFile);
            int objCount = 0;
            reader.useDelimiter(",");
            while (reader.hasNext()){
                System.out.println("nutha 1");
                reader.reset();
                while (reader.hasNext()){
                    obstaclesArrayList.add(new Obstacle(reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextLine().replace(" ","")));
                    System.out.println(obstaclesArrayList.get(0).type);
                    System.out.println("zen");
                }
                reader.useDelimiter(",");
                objCount++;
            }
            System.out.println("debug");
            Obstacle[] obstacles = (Obstacle[]) obstaclesArrayList.toArray();
            return obstacles;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}