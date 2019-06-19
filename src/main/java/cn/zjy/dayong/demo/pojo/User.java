package cn.zjy.dayong.demo.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjy
 * @since 2018-08-29
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
	private Integer age;
	private String address;
	private String userGender;
	private String username;
	private String password;
	private String userPhone;
	private String userEmail;

}
