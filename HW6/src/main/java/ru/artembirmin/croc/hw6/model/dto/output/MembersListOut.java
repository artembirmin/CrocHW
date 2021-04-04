package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "people")
public class MembersListOut {

    @XmlElement(name = "person")
    private List<MemberOut> members;

    public MembersListOut() {
    }

    public List<MemberOut> getMembers() {
        return members;
    }

    public void setMembers(List<MemberOut> members) {
        this.members = members;
    }
}
