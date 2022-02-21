package priv.thinkam.toycode.generator.gene2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import lombok.Data;

@Data
public class ExcelData implements IExcelDataModel {
    @Excel(name = "变量名")
    private String variableName;

    @Excel(name = "含义")
    private String comment;

    @Excel(name = "类型")
    private String type;

    @Excel(name = "备注")
    private String remark;

    // -------------------

    private Integer rowNum = 0;

    @Override
    public Integer getRowNum() {
        return rowNum + 1;
    }

    @Override
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public ExcelData(String variableName, String comment) {
        this.variableName = variableName;
        this.comment = comment;
    }

    public ExcelData() {
    }
}