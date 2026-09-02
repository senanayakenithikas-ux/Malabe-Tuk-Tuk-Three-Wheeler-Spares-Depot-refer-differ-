import java.io.*;
import java.util.*;
import java.util.function.DoubleBinaryOperator;


public class InventoryCleaner {
    private static final String legacy_path = "data/inventory_legacy.txt";
    private static final String clean_path = "data/inventory_clean.txt";

    public void cleanIfNeed(){
        File cleanFile = new File(clean_path);
        if (cleanFile.exists()){
            return;
        }
        clean();
    }

    public void clean(){
        List<Part> parts = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(legacy_path));
            String line;

            while((line = reader.readLine()) != null){
                if (line.trim().isEmpty()){
                    continue;
                }

                try {
                    char delimiter = detectDelimiter(line);
                    String[] fields = line.split("\\"+delimiter);

                    String code = fields.length > 0 ? fields[0].trim() : "";
                    String name = cleanText(fields.length > 1 ? fields[1]: "","name");
                    String brand = cleanText(fields.length > 2 ? fields[2]: "","brand");
                    double price = cleanPrice(fields.length > 3 ? fields[3]: "");
                    int quantity = cleanQuantity(fields.length > 4 ? fields[4]: "");
                    String category = cleanCategory(fields.length > 5 ? fields[5]: "");
                    String date  = cleanDate(fields.length > 6 ? fields[6]: "");
                    String image = cleanText(fields.length > 7 ? fields[7]: "","image");

                    int threshold = 10;

                    if(code.isEmpty()){
                        continue;
                    }
                    Part part = new Part(code,name,brand,price,quantity,category,date,image,threshold);
                    parts.add(part);

                } catch (Exception e){
                    continue;
                }
            }
            reader.close();
        }catch (IOException e){
            System.out.println("could not read legacy inventory file.");
        }
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(clean_path));
            for (Part part : parts){
                writer.println(part.toFileLine());
            }
            writer.close();
        } catch (IOException e){
            System.out.println("could not write clean inventory file.");
        }
    }

    private char detectDelimiter(String line){
        int commas = 0;
        int semicolons = 0;
        int pipes = 0;
        for (int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            if (c == ',') commas++;
            if (c == ';') semicolons++;
            if (c == '|') pipes++;
        }
        if (semicolons >= commas && semicolons >= pipes) return ';';
        if (pipes >= commas && pipes >= semicolons) return '|';
        return ',';
    }

    private String cleanText(String field, String fieldName){
        String trimmed = field.trim();
        if (trimmed.isEmpty()){
            return "No " + fieldName;
        }
        return trimmed;
    }

    private double cleanPrice(String field){
        String cleaned = field.trim();
        cleaned = cleaned.replace("Rs.","").replace("Rs", "").replace("rs", "").trim();
        try {
            return Double.parse

        }    }
}
