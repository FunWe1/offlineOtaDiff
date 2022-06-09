package com.hxzj.javafxboot.view;

import lombok.Data;

@Data
public class DataTable {
    public String sn;
    public String id;
    public String baseVer;
    public String baseFileSize;
    public String upgradeVer;
    public String upgradeFileSize;
    public String diffFileSize;
    public String compression;
    public String createTime;
    public String find;
    public String delete;
}
