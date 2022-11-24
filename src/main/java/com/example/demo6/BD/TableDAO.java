package com.example.demo6.BD;

import com.example.demo6.ResultTable;

import java.util.List;

public interface TableDAO {
    public ResultTable findById(int id);

    public void save(ResultTable resultTable);

    public void delete(ResultTable resultTable);

    public List<ResultTable> findAll();

}
