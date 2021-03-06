package pojogroup;

import com.yanxuemeng.core.pojo.specification.Specification;
import com.yanxuemeng.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
     * 包装对象 组合对象
     * 规格对象
     * 规格选项结果集对象
     * 不实现序列化接口行不行?   不行   抛异常 SSM 小练习
     */
    public class SpecificationVo implements Serializable {


        //规格对象
        private Specification specification;

        //规格选项结果集对象
        private List<SpecificationOption> specificationOptionList;

        public Specification getSpecification() {
            return specification;
        }

        public void setSpecification(Specification specification) {
            this.specification = specification;
        }

        public List<SpecificationOption> getSpecificationOptionList() {
            return specificationOptionList;
        }

        public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
            this.specificationOptionList = specificationOptionList;
        }
    }

