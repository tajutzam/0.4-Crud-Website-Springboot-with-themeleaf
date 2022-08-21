package zam.dev.demomvc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;



@Data
public class RespondBodyDto<T>{

    private List<String> message = new ArrayList<>();

    private T payLoad;
    

}