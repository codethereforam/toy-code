import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model  
 *
 * @author yanganyu
 * @date ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Model {
<#list excelDataList! as v>
    @ApiModelProperty(value = "${v.comment}")
    private ${v.type} ${v.variableName};
    
</#list>
}