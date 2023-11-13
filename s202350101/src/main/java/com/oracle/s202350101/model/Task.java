package com.oracle.s202350101.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Date 
@Data
//@ScriptAssert(lang="javascripte" script="", message="")
public class Task {
		private int		task_id;
		private int		project_id;
		private int		project_step_seq;
		private String	user_id;

		@NotEmpty(message = "이 항목은 필수입니다")
		private String	task_subject;
		@NotEmpty(message = "이 항목은 필수입니다")
		private String	task_content;

		private java.sql.Date task_stat_time;

		private java.sql.Date	task_end_itme;
		@NotNull(message = "이 항목은 필수입니다")
		private String	task_priority;
		@NotNull(message = "이 항목은 필수입니다")
		private String	task_status;
		private int		garbage;
		
		//읽기 전용 
		private int status_0_count;
		private int status_1_count;
		private int status_2_count;
		private String project_s_name;
		private String user_name;

		private String search;   	private String keyword;
		private String pageNum;
		private int start; 		 	private int end;
		private int rn;
}