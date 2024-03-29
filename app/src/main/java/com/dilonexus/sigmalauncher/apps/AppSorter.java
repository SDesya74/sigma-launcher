package com.dilonexus.sigmalauncher.apps;

import java.io.Serializable;

public class AppSorter implements Serializable {
    public enum SortType implements Serializable{
        APP_NAME, INSTALL_TIME, POPULARITY
    }

    public static int compareApps(SortType type, AppData first, AppData second){
        assert type != null;

        switch (type){
            case APP_NAME:
                return first.getName().compareTo(second.getName());

            case POPULARITY:
                int x = first.getPopularity();
                int y = second.getPopularity();
                return (x < y) ? -1 : ((x == y) ? 0 : 1); // Integer.compare requires API 19

            case INSTALL_TIME:
                long a = first.installTime;
                long b = second.installTime;
                return (a < b) ? -1 : ((a == b) ? 0 : 1);
        }
        return 0;
    }
}
