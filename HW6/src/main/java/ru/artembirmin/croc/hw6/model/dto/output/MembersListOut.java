package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "people")
public class MembersListOut {

    @XmlElement(name = "person")
    private List<MemberOut> members;

    public MembersListOut() {
    }

    public MembersListOut(List<MemberOut> members) {
        this.members = members;
    }

    public List<MemberOut> getMembers() {
        return members;
    }

    public void setMembers(List<MemberOut> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembersListOut)) return false;
        MembersListOut that = (MembersListOut) o;
        return Objects.equals(getMembers(), that.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMembers());
    }
}
