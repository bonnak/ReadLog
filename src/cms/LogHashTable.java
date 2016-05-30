package cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class LogHashTable {

    private Map<Integer, String> time_map;
    private Map<Integer, String> machine_map;
    private Map<Integer, String> event_map;
    private Map<Integer, String> event_id_map;
    private Map<Integer, String> event_status_map;
    private int increments;

    /**
     * Instantiate a new instance.
     */
    public LogHashTable() {
        this.time_map = new HashMap<Integer, String>();
        this.machine_map = new HashMap<Integer, String>();
        this.event_map = new HashMap<Integer, String>();
        this.event_id_map = new HashMap<Integer, String>();
        this.event_status_map = new HashMap<Integer, String>();
        
        this.increments = 0;
    }

    /***
     * Insert a new record into the HashMaps.
     * 
     * @param java.util.Map<Integer, String> time
     * @param java.util.Map<Integer, String> machine_name
     * @param java.util.Map<Integer, String> event
     * @param java.util.Map<Integer, String> event_id
     * @param java.util.Map<Integer, String> event_status 
     */
    public void insert(String time, String machine_name, String event, String event_id, String event_status) {
        this.time_map.put(this.increments, time);
        this.machine_map.put(this.increments, machine_name);
        this.event_map.put(this.increments, event);
        this.event_id_map.put(this.increments, event_id);
        this.event_status_map.put(this.increments, event_status);

        this.increments++;
    }
    
    /**
     * Get all data.
     * 
     * @return List<String>
     */
    public List<String> all(){
        List<String> list = new ArrayList<String>();
        
        for(int key=0; key<this.increments; key++){
            list.add(
                    this.time_map.get(key) + " "
                    + this.machine_map.get(key) + " "
                    + this.event_map.get(key) + " "
                    + this.event_id_map.get(key) + " "
                    + this.event_status_map.get(key)
            );
        }
        
        return list;
    }

    /**
     * Search by machine name.
     * 
     * @param String machine_name 
     */
    public List<String> searchByMachine(String machine_name) {
        
        return this.mapKeyWord(this.machine_map, machine_name).get(machine_name);
    }
    
    /**
     * Search by event status.
     * 
     * @param String status 
     */
    public List<String> searchByEventStatus(String status) {
                
        return this.mapKeyWord(this.event_status_map, status).get(status);
    }

    /**
     * Get the id list of a value.
     *
     * @param java.util.Map hm
     * @param Object value
     *
     * @return java.util.List<String>
     */
    private List<Integer> getKeyByValue(Map hm, Object value) {

        List<Integer> list = new ArrayList<Integer>();

        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                list.add((Integer) o);
            }
        }
        return list;
    }

    /**
     * Map the keyword with the related original hash table.
     * 
     * @param java.util.Map<Integer, String> hm
     * @param String keyword
     * 
     * @return java.util.Map<String, List<String>>
     */
    private Map<String, List<String>> mapKeyWord(Map hm, String keyword) {

        Map<String, List<String>> hmap = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();

        ListIterator list_iterator = this.getKeyByValue(hm, keyword).listIterator();
        while (list_iterator.hasNext()) {
            Integer key = (Integer) list_iterator.next();

            list.add(
                    this.time_map.get(key) + " "
                    + this.machine_map.get(key) + " "
                    + this.event_map.get(key) + " "
                    + this.event_id_map.get(key) + " "
                    + this.event_status_map.get(key)
            );
        }
        
        hmap.put(keyword, list);
        
        return hmap;
    }
    
    
    /**
     * Remove old data and set members to its default state.
     */
    private void resetMembers(){
        this.time_map.clear();
        this.machine_map.clear();
        this.event_map.clear();
        this.event_id_map.clear();
        this.event_status_map.clear();
        
        this.increments = 0;
    }
}
