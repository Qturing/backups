package org.jn.cqlDriver;



import java.io.*;

public class CqlMonitor {
    private String taskID;
    private String startTime;
    private String endTime;
    private String jobId;
    private String status;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void parseTaskLog(String taskID, String fileName) {

        this.taskID = taskID;

        File file = new File(fileName);
//        File file = new File("/home/apache-storm-1.0.6/logs/nimbus.log");
//        File file = new File("D:\qxf\20181031_storm.log");
        if (file.exists()) {

            FileInputStream fis = null; //Construct BufferedReader from InputStreamReader
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;

            String res = "";

            try {
                while ((line = br.readLine()) != null) {
                    //                System.out.println(line);

                    if (line.contains("Received topology submission for cql_" + taskID)) {
                        setStartTime(line.split(" ")[0] + " " + line.split(" ")[1]);
                        //setJobId(line.split(" ")[6]);
                        setJobId(line.substring(line.indexOf("storm.id")+11,line.indexOf("topology.name")-4).trim());
                    }

                    if (line.contains("Killing topology: cql_" + taskID) || line.contains("Cleaning up cql_" + taskID)) {
                        res = "finished";
                    }

                    if (line.contains("Activating cql_" + taskID)) {
                        //RUNNING
                        res = "running";
                    }
                    setStatus(res);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    /*
    public static void main(String[] args){
        CqlMonitor cqlMonitor = new CqlMonitor();
        cqlMonitor.parseTaskLog("qq","D:\\qxf\\20181031_storm.log");
        System.out.println(cqlMonitor.getStatus());
        System.out.println(cqlMonitor.getStartTime());
        System.out.println(cqlMonitor.getJobId());
    }
    */
}
