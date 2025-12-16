package app.training.dia.response;

import app.training.dia.dto.JobDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobDetailResponse extends BaseResponse{
    private JobDto data;
}
