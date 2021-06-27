package com.xx.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "duty")
public class Duty {

    private String weeks;
    private String time;
    private String type;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

}
