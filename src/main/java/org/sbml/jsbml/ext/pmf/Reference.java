package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.TreeMap;

/**
 * Literature item
 * <p>
 * <p>
 * Example:
 * <code>&lt;pmf:reference author="Baranyi" ... /&gt;</code>
 *
 * @author Miguel Alba
 */
public class Reference extends AbstractSBase {

    public enum ReferenceType {
        Paper(1), SOP(2), LA(3), Handbuch(4), Laborbuch(5), Buch(6), Webseite(7), Bericht(8);

        private int value;

        ReferenceType(final int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static ReferenceType valueOf(final int value) {
            return ReferenceType.values()[value - 1];
        }
    }


    private enum Tag {
        author("AU"),
        year("PY"),
        title("TI"),
        abstract_text("AB"),
        journal("T2"),
        volume("VL"),
        issue("IS"),
        page("SP"),
        approval_mode("LB"),
        website("UR"),
        type("M3"),
        comment("N1");

        String value;

        Tag(String value) {
            this.value = value;
        }
    }

    private String author;
    private Integer year;
    private String title;
    private String abstractText;
    private String journal;
    private String volume;
    private String issue;
    private Integer page;
    private Integer approvalMode;
    private String website;
    private ReferenceType type;
    private String comment;

    /**
     * Creates a Reference instance.
     */
    public Reference() {
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Creates a Reference instance with a level and version.
     */
    public Reference(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public Reference(Reference obj) {
        super(obj);
        this.author = obj.author;
        this.year = obj.year;
        this.title = obj.title;
        this.abstractText = obj.abstractText;
        this.journal = obj.journal;
        this.volume = obj.volume;
        this.issue = obj.issue;
        this.page = obj.page;
        this.approvalMode = obj.approvalMode;
        this.website = obj.website;
        this.type = obj.type;
        this.comment = obj.comment;

        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clones this class.
     */
    @Override
    public Reference clone() {
        return new Reference(this);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        boolean isAttributeRead;

        if (attributeName.equals(Tag.author.value)) {
            author = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.year.value)) {
            year = StringTools.parseSBMLInt(value);
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.title.value)) {
            title = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.abstract_text.value)) {
            abstractText = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.journal.value)) {
            journal = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.volume.value)) {
            volume = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.issue.value)) {
            issue = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.page.value)) {
            page = StringTools.parseSBMLInt(value);
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.approval_mode.value)) {
            approvalMode = StringTools.parseSBMLInt(value);
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.website.value)) {
            website = value;
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.type.value)) {
            type = ReferenceType.valueOf(value);
            isAttributeRead = true;
        } else if (attributeName.equals(Tag.comment.value)) {
            comment = value;
            isAttributeRead = true;
        } else {
            isAttributeRead = false;
        }

        return isAttributeRead;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();

        if (isSetAuthor())
            attributes.put(Tag.author.value, author);
        if (isSetYear())
            attributes.put(Tag.year.value, StringTools.toString(year));
        if (isSetTitle())
            attributes.put(Tag.title.value, title);
        if (isSetAbstractText())
            attributes.put(Tag.abstract_text.value, abstractText);
        if (isSetJournal())
            attributes.put(Tag.journal.value, journal);
        if (isSetVolume())
            attributes.put(Tag.volume.value, volume);
        if (isSetIssue())
            attributes.put(Tag.issue.value, issue);
        if (isSetPage())
            attributes.put(Tag.page.value, StringTools.toString(page));
        if (isSetApprovalMode())
            attributes.put(Tag.approval_mode.value, StringTools.toString(approvalMode));
        if (isSetWebsite())
            attributes.put(Tag.website.value, website);
        if (isSetType())
            attributes.put(Tag.type.value, type.name());
        if (isSetComment())
            attributes.put(Tag.comment.value, comment);

        return attributes;
    }

    @Override
    public String toString() {
        return author + "_" + year + "_" + title;
    }

    // --- author attribute ---
    public String getAuthor() {
        return isSetAuthor() ? author : null;
    }

    public boolean isSetAuthor() {
        return author != null;
    }

    public void setAuthor(String author) {
        String oldAuthor = this.author;
        this.author = author;
        firePropertyChange(Tag.author.value, oldAuthor, this.author);
    }

    public boolean unsetAuthor() {
        if (isSetAuthor()) {
            String oldAuthor = author;
            author = null;
            firePropertyChange(Tag.author.value, oldAuthor, author);
            return true;
        }
        return false;
    }

    // --- year attribute ---
    public int getYear() {
        if (isSetYear()) {
            return year;
        }
        // This is necessary if we cannot return null here
        throw new PropertyUndefinedError(Tag.year.value, this);
    }

    public boolean isSetYear() {
        return year != null;
    }

    public void setYear(int year) {
        Integer oldYear = this.year;
        this.year = year;
        firePropertyChange(Tag.year.value, oldYear, year);
    }

    public boolean unsetYear() {
        if (isSetYear()) {
            Integer oldYear = year;
            year = null;
            firePropertyChange(Tag.year.value, oldYear, year);
            return true;
        }
        return false;
    }

    // --- title attribute ---
    public String getTitle() {
        return isSetTitle() ? title : null;
    }

    public boolean isSetTitle() {
        return title != null;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        firePropertyChange(Tag.title.value, oldTitle, title);
    }

    public boolean unsetTitle() {
        if (isSetTitle()) {
            String oldTitle = title;
            title = null;
            firePropertyChange(Tag.title.value, oldTitle, title);
            return true;
        }
        return false;
    }

    // --- abstractText attribute ---
    public String getAbstractText() {
        return isSetAbstractText() ? abstractText : null;
    }

    public boolean isSetAbstractText() {
        return abstractText != null;
    }

    public void setAbstractText(String abstractText) {
        String oldAbstractText = this.abstractText;
        this.abstractText = abstractText;
        firePropertyChange(Tag.abstract_text.value, oldAbstractText, abstractText);
    }

    public boolean unsetAbstractText() {
        if (isSetAbstractText()) {
            String oldAbstractText = abstractText;
            abstractText = null;
            firePropertyChange(Tag.abstract_text.value, oldAbstractText, abstractText);
            return true;
        }
        return false;
    }

    // --- journal attribute ---
    public String getJournal() {
        return isSetJournal() ? journal : null;
    }

    public boolean isSetJournal() {
        return journal != null;
    }

    public void setJournal(String journal) {
        String oldJournal = this.journal;
        this.journal = journal;
        firePropertyChange(Tag.journal.value, oldJournal, journal);
    }

    public boolean unsetJournal() {
        if (isSetJournal()) {
            String oldJournal = journal;
            journal = null;
            firePropertyChange(Tag.journal.value, oldJournal, journal);
            return true;
        }
        return false;
    }

    // --- volume attribute ---
    public String getVolume() {
        return isSetVolume() ? volume : null;
    }

    public boolean isSetVolume() {
        return volume != null;
    }

    public void setVolume(String volume) {
        String oldVolume = this.volume;
        this.volume = volume;
        firePropertyChange(Tag.volume.value, oldVolume, volume);
    }

    public boolean unsetVolume() {
        if (isSetVolume()) {
            String oldVolume = volume;
            volume = null;
            firePropertyChange(Tag.volume.value, oldVolume, volume);
            return true;
        }
        return false;
    }

    // --- issue attribute ---
    public String getIssue() {
        return isSetIssue() ? issue : null;
    }

    public boolean isSetIssue() {
        return issue != null;
    }

    public void setIssue(String issue) {
        String oldIssue = this.issue;
        this.issue = issue;
        firePropertyChange(Tag.issue.value, oldIssue, issue);
    }

    public boolean unsetIssue() {
        if (isSetIssue()) {
            String oldIssue = issue;
            issue = null;
            firePropertyChange(Tag.issue.value, oldIssue, issue);
            return true;
        }
        return false;
    }

    // --- page attribute ---
    public int getPage() {
        if (isSetPage()) {
            return page;
        }
        // This is necessary if we cannot return null here
        throw new PropertyUndefinedError(Tag.page.value, this);
    }

    public boolean isSetPage() {
        return page != null;
    }

    public void setPage(int page) {
        Integer oldPage = this.page;
        this.page = page;
        firePropertyChange(Tag.page.value, oldPage, page);
    }

    public boolean unsetPage() {
        if (isSetPage()) {
            Integer oldPage = page;
            page = null;
            firePropertyChange(Tag.page.value, oldPage, page);
            return true;
        }
        return false;
    }

    // --- approvalMode attribute ---
    public int getApprovalMode() {
        if (isSetApprovalMode()) {
            return approvalMode;
        }
        // This is necessary if we cannot return null here
        throw new PropertyUndefinedError(Tag.approval_mode.value, this);
    }

    public boolean isSetApprovalMode() {
        return approvalMode != null;
    }

    public void setApprovalMode(int approvalMode) {
        Integer oldApprovalMode = this.approvalMode;
        this.approvalMode = approvalMode;
        firePropertyChange(Tag.approval_mode.value, oldApprovalMode, approvalMode);
    }

    public boolean unsetApprovalMode() {
        if (isSetApprovalMode()) {
            Integer oldApprovalMode = approvalMode;
            approvalMode = null;
            firePropertyChange(Tag.approval_mode.value, oldApprovalMode, approvalMode);
            return true;
        }
        return false;
    }

    // --- website attribute ---
    public String getWebsite() {
        return isSetWebsite() ? website : null;
    }

    public boolean isSetWebsite() {
        return website != null;
    }

    public void setWebsite(String website) {
        String oldWebsite = this.website;
        this.website = website;
        firePropertyChange(Tag.website.value, oldWebsite, website);
    }

    public boolean unsetWebsite() {
        if (isSetWebsite()) {
            String oldWebsite = website;
            website = null;
            firePropertyChange(Tag.website.value, oldWebsite, website);
            return true;
        }
        return false;
    }

    // --- type attribute ---
    public ReferenceType getType() {
        return isSetType() ? type : null;
    }

    public boolean isSetType() {
        return type != null;
    }

    public void setType(ReferenceType type) {
        ReferenceType oldType = this.type;
        this.type = type;
        firePropertyChange(Tag.type.value, oldType, type);
    }

    public boolean unsetType() {
        if (isSetType()) {
            ReferenceType oldType = type;
            type = null;
            firePropertyChange(Tag.type.value, oldType, type);
            return true;
        }
        return false;
    }

    // --- comment attribute ---
    public String getComment() {
        return isSetComment() ? comment : null;
    }

    public boolean isSetComment() {
        return comment != null;
    }

    public void setComment(String comment) {
        String oldComment = this.comment;
        this.comment = comment;
        firePropertyChange(Tag.comment.value, oldComment, comment);
    }

    public boolean unsetComment() {
        if (isSetComment()) {
            String oldComment = comment;
            comment = null;
            firePropertyChange(Tag.comment.value, oldComment, comment);
            return true;
        }
        return false;
    }
}
