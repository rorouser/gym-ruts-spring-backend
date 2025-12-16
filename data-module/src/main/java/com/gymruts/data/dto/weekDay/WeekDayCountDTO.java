package com.gymruts.data.dto.weekDay;

import com.gymruts.data.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeekDayCountDTO {
	
	private WeekDay weekday;
    private Long count;
    


}
