package com.bobandata.iot.entity.dms;

import javax.persistence.*;

@Entity
public class TaskItem {
//    TASK_ID       任务ID    int
//    TASK_NAME     任务名    varchar
//    TASK_VALUE    任务值    int
//    TASK_DSC      任务描述   varchar
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    @Column(name = "TASK_NAME",length = 20)
    private String taskName;
    @Column(name = "TASK_VALUE")
    private Integer taskVaule;
    @Column(name = "TASK_DSC")
    private String taskDsc;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskVaule() {
        return taskVaule;
    }

    public void setTaskVaule(Integer taskVaule) {
        this.taskVaule = taskVaule;
    }

    public String getTaskDsc() {
        return taskDsc;
    }

    public void setTaskDsc(String taskDsc) {
        this.taskDsc = taskDsc;
    }
}
