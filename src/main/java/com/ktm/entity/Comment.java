package com.ktm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ktm
 * @since 2020-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

     static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
     Integer id;

     Integer parentId;

     Integer type;

     Integer commentator;

     LocalDate createTime;

     LocalDate modifiedTime;

     Integer likeCount;

     String content;


}
