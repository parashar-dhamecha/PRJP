package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Response.Data;

import java.util.ArrayList;

public interface ObserverListener {
    void onobserverselected(Data v);

    void onobserverunselected(Data v);
}
