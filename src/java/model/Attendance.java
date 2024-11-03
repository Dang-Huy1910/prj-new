/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Attendance {
    private int atid;
    private WorkAssignment waid;
    private int actualQuantity;
    private float alpha;
    private String note;

    public Attendance(int atid, WorkAssignment waid, int actualQuantity, float alpha, String note) {
        this.atid = atid;
        this.waid = waid;
        this.actualQuantity = actualQuantity;
        this.alpha = alpha;
        this.note = note;
    }

    public WorkAssignment getWaid() {
        return waid;
    }

    public void setWaid(WorkAssignment waid) {
        this.waid = waid;
    }

    

    public int getAtid() {
        return atid;
    }

    public void setAtid(int atid) {
        this.atid = atid;
    }



    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
