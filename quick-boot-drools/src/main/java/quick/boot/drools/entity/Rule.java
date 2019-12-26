package quick.boot.drools.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Rule implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false, unique = true)
  private String ruleKey;
  @Column(nullable = false)
  private String content;
  @Column(nullable = false, unique = true)
  private String version;
  @Column(nullable = true, unique = true)
  private String lastModifyTime;
  @Column(nullable = false)
  private String createTime;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}