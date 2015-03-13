package com.oursky.todo_android.content.model;

import java.util.Date;

/**
 * Created by yuyauchun on 13/3/15.
 */

public class Item {
    private String task;
    private Date dueDate;
    private boolean finished = false;

    public Item(String task) {
        this.task = task;
    }

    public Item(String task, Date dueDate) {
        this.task = task;
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isTaskFinished() {
        return finished;
    }

    public void setisFinished(boolean finished) {
        this.finished = finished;
    }
}
