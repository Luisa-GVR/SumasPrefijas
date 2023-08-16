import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static final String SEPARADOR =",";
    public static void main(String[] args) throws IOException {

        String csvFile = "src/car_sales.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        int numRows = 0;
        int numCols = 0;
        String line;

        // Contar el número de filas y columnas
        while ((line = br.readLine()) != null) {
            numRows++;
            String[] row = line.split(SEPARADOR);
            numCols = Math.max(numCols, row.length);
        }
        br.close();
        br = new BufferedReader(new FileReader(csvFile));

        //Se crea el array con la informacion TOTAL
        String[][] dataArray = new String[numRows][numCols];
        int rowIdx = 0;

        while ((line = br.readLine()) != null) {
            String[] row = line.split(SEPARADOR);
            System.arraycopy(row, 0, dataArray[rowIdx], 0, row.length);
            rowIdx++;
        }
        br.close();

        int lineaLeer = 0;
        for (int i = 0; i < numCols; i++) {
            if(dataArray[0][i].equals("sales")){
                lineaLeer = i;
            }
        }

        //Array con unicamente las sales
        double[] salesNumber = new double[numRows];
        for (int i = 1; i < salesNumber.length ; i++) {
            String tempLine = dataArray[i][lineaLeer].substring(1);
            salesNumber[i] = Double.parseDouble(tempLine);
        }

        double suma=0;
        for (int i = 1; i < salesNumber.length; i++) {
            suma += salesNumber[i];
            System.out.printf("%d. %.2f%n", i, suma);
        }

    }
}
