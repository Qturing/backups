package org.jn.cqlDriver;


import bean.ResultDto;
import com.alibaba.fastjson.JSON;
import com.huawei.streaming.cql.Driver;
import com.huawei.streaming.cql.exception.CQLException;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CqlDriver {
    /**
     * 1、运行cql语句，生成cql文件
     */
    private String taskId;
    private String jsonStr;


    public CqlDriver() {
    }

    public CqlDriver(String taskId, String jsonStr) {
        this.taskId = taskId;
        this.jsonStr = jsonStr;
    }

    public String getJsonstr() {
        return jsonStr;
    }

    public void setJsonstr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int genCql(String path) throws IOException,CQLException {

        if(this.jsonStr != null){
            ResultDto resultDto = JSON.parseObject(jsonStr,ResultDto.class);
            String filePath =  path + "/cql_" + this.taskId;
            OutputStream os = new FileOutputStream(filePath);
            PrintWriter cqlFile = new PrintWriter(os);
            Driver cqlDriver = new Driver();
            //ADD JAR
            String jarName = resultDto.getData().getTaskRules().get(0).getFiles().get(0).getFileName();
            if(jarName.isEmpty()){
                jarName = "CqlExample.jar";
            }
            String strAddJar = "ADD JAR \"/home/stream-cql-bianry-storm-2.0/bin/"+ jarName +"\"";
            //String strAddJar = "ADD JAR \"/home/stream-cql-bianry-storm-2.0/bin/CqlExample.jar\"";
            cqlFile.write(strAddJar + ";\n");
            cqlDriver.run(strAddJar);
            //INPUT
            String strSerdeSeperator = ",";
            if(!resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getSeparatorTag().isEmpty()){

                strSerdeSeperator = resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getSeparatorTag();
            }

            String strSourceTopic = resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getTopic();
            StringBuilder strProperties = new StringBuilder("(");
            int propertiesSize = resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getDataSourceFields().size();
            for(int i = 0 ; i < propertiesSize ; i++){
                strProperties.append(resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getDataSourceFields().get(i).getFieldName() + " ");
                strProperties.append(resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getDataSourceFields().get(i).getFieldType().toUpperCase() + ",");
            }
            strProperties.deleteCharAt(strProperties.length()-1);
            strProperties.append(")");
            //String strGroupId = resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getGroupId();
            String strGroupId = "cql_" + this.taskId;
            //String strInputZookeepers = resultDto.getData().getTaskRules().get(0).getDataSources().get(0).getConnectionUrl();
            String strInput = "CREATE INPUT STREAM s\n" +
                    strProperties.toString() + "\n" +
                    "SERDE simpleSerde\n" +
                    "PROPERTIES ( \"separator\" = \"" + strSerdeSeperator + "\" )\n" +
                    "SOURCE KafkaInput\n" +
                    "PROPERTIES ( \"operator.kafka.groupid\"=\""+ strGroupId +"\"," +
                    "\"operator.kafka.topic\"=\"" + strSourceTopic + "\",\"operator.kafka.zookeepers\"=" +
                    "\"172.16.126.20:2181\")";
            cqlFile.write(strInput + ";\n");
            cqlDriver.run(strInput);
            //OUTPUT
            String strSinkTopic = resultDto.getData().getTaskRules().get(0).getDataTarget().getTopic();
            String strSinkZookeepers = resultDto.getData().getTaskRules().get(0).getDataTarget().getConnectionUrl();
            String strSinkBroker =resultDto.getData().getTaskRules().get(0).getDataTarget().getConnectionUrl();
            String strOutput = "CREATE OUTPUT STREAM rs\n" +
                    strProperties.toString() + "\n" +
                    "SINK KafkaOutput\n" +
                    "PROPERTIES ( \"operator.kafka.topic\"=\"" + strSinkTopic +
                    "\",\"operator.kafka.zookeepers\"=\""+ strSinkZookeepers +"\"," +
                    "\"operator.kafka.brokers\"=\""+ strSinkBroker +"\")";
            cqlFile.write(strOutput + ";\n");
            cqlDriver.run(strOutput);
            //CREATE OPERATOR
            String strOperator = "CREATE OPERATOR userOp AS \"org.jn.streamcql.userdefined.datasource.Operator\"\n" +
                    "INPUT\n" +
                    strProperties.toString() + "\n" +
                    "OUTPUT\n" +
                    strProperties.toString() + "\n" +
                    "PROPERTIES (\"userop.filename\" = \"" + this.taskId + "\")";
            cqlFile.write(strOperator + ";\n");
            cqlDriver.run(strOperator);
            //INSERT
            String strInsert = "INSERT INTO rs USING OPERATOR userOp FROM s PARALLEL 2";
            cqlFile.write(strInsert +";\n");
            cqlDriver.run(strInsert);
            //SUBMIT
            String strSubmit = "SUBMIT APPLICATION FORCE cql_" + this.taskId;
            cqlFile.write(strSubmit + ";");
            cqlDriver.run(strSubmit);
            cqlFile.close();
            os.close();
            return 1;
        }else{
            System.out.println("未检测到输入数据！");
            return 0;
        }

    }
}
