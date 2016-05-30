package cms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private LogHashTable hash_table;
    private String report_dir;

    public Log() {
        this.hash_table = new LogHashTable();
        this.report_dir = "Report";
    }

    public void readFile(String file_name) throws IOException {

        Scanner file = new Scanner(new File(file_name));
        file.nextLine(); // Omit the first line.

        while (file.hasNextLine()) {
            String[] split = file.nextLine().split(" ");

            this.hash_table.insert(split[0], split[1], split[2], split[3], split[4]);
        }
        file.close();
        
        System.out.println("Import log successfully");
    }

    /**
     * View events base on machine.
     */
    public void listMachineReport(String machine_name) {
        List<String> list = this.hash_table.searchByMachine(machine_name);

        if (list.isEmpty()) {
            System.out.println("Sorry, there're no event of machine '" + machine_name + "'");
            return;
        }

        System.out.println("Events for machine '" + machine_name + "'");
        this.showEvent(list);
        System.out.println("End of events");
    }
    
    /**
     * Export report by machine name.
     * 
     * @param machine_name 
     */
    public void exportReportByMachine(String machine_name){
        List<String> list = this.hash_table.searchByMachine(machine_name);
        
        if (list.isEmpty()) {
            System.out.println("Sorry, there're no event of machine '" + machine_name + "'");
            return;
        }
        
        try {
            this.writeFile(list, "Machine-" + machine_name);
            System.out.println("Export file successfully.");
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * View failed events.
     */
    public void listFailedEvent() {
        List<String> list = this.hash_table.searchByEventStatus("Failed");

        if (list.isEmpty()) {
            System.out.println("Sorry, there're no failed events");
            return;
        }

        this.showEvent(list);
    }

    /**
     * Export report in which events are failed.
     *
     * @throws IOException
     */
    public void exportFailedEventReport() {
        List<String> list = this.hash_table.searchByEventStatus("Failed");

        if (list.isEmpty()) {
            System.out.println("Sorry, there're no failed events");
            return;
        }
        
        try {
            this.writeFile(list, "FailedEvent");
            System.out.println("Export file successfully.");
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Show all log report.
     */
    public void ShowAllLog(){
        List<String> list = this.hash_table.all();
        
        this.showEvent(list);
    }

    /**
     * Write a new report file.
     * 
     * @param list
     * @param file_name
     * 
     * @throws IOException 
     */
    private void writeFile(List<String> list, String file_name) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        File file = new File(this.report_dir + "/" + file_name + "-" + dateFormat.format(date) + "-" + date.getTime() + ".txt");
        FileWriter writer = new FileWriter(file);

        // Writes content to the file
        ListIterator itr = list.listIterator();
        while (itr.hasNext()) {
            writer.write(itr.next() + "\r\n");
        }

        writer.flush();
        writer.close();
    }

    /**
     * Print data on screen.
     * 
     * @param list 
     */
    private void showEvent(List<String> list) {
        ListIterator itr = list.listIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
