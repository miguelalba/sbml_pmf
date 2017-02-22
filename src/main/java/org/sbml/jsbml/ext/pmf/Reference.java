package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.Objects;
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
        switch (attributeName) {
            case PmfConstants.ref_author:
                author = value;
                return true;
            case PmfConstants.ref_year:
                year = StringTools.parseSBMLInt(value);
                return true;
            case PmfConstants.ref_title:
                title = value;
                return true;
            case PmfConstants.ref_abstractText:
                abstractText = value;
                return true;
            case PmfConstants.ref_journal:
                journal = value;
                return true;
            case PmfConstants.ref_volume:
                volume = value;
                return true;
            case PmfConstants.ref_issue:
                issue = value;
                return true;
            case PmfConstants.ref_page:
                page = StringTools.parseSBMLInt(value);
                return true;
            case PmfConstants.ref_approvalMode:
                approvalMode = StringTools.parseSBMLInt(value);
                return true;
            case PmfConstants.ref_website:
                website = value;
                return true;
            case PmfConstants.ref_type:
                type = ReferenceType.valueOf(value);
                return true;
            case PmfConstants.ref_comment:
                comment = value;
                return true;
            default:
                return false;
        }

    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();

        if (isSetAuthor())
            attributes.put(PmfConstants.ref_author, author);
        if (isSetYear())
            attributes.put(PmfConstants.ref_year, StringTools.toString(year));
        if (isSetTitle())
            attributes.put(PmfConstants.ref_title, title);
        if (isSetAbstractText())
            attributes.put(PmfConstants.ref_abstractText, abstractText);
        if (isSetJournal())
            attributes.put(PmfConstants.ref_journal, journal);
        if (isSetVolume())
            attributes.put(PmfConstants.ref_volume, volume);
        if (isSetIssue())
            attributes.put(PmfConstants.ref_issue, issue);
        if (isSetPage())
            attributes.put(PmfConstants.ref_page, StringTools.toString(page));
        if (isSetApprovalMode())
            attributes.put(PmfConstants.ref_approvalMode, StringTools.toString(approvalMode));
        if (isSetWebsite())
            attributes.put(PmfConstants.ref_website, website);
        if (isSetType())
            attributes.put(PmfConstants.ref_type, type.name());
        if (isSetComment())
            attributes.put(PmfConstants.ref_comment, comment);

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
        firePropertyChange(PmfConstants.ref_author, oldAuthor, this.author);
    }

    public boolean unsetAuthor() {
        if (isSetAuthor()) {
            String oldAuthor = author;
            author = null;
            firePropertyChange(PmfConstants.ref_author, oldAuthor, author);
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
        throw new PropertyUndefinedError(PmfConstants.ref_year, this);
    }

    public boolean isSetYear() {
        return year != null;
    }

    public void setYear(int year) {
        Integer oldYear = this.year;
        this.year = year;
        firePropertyChange(PmfConstants.ref_year, oldYear, year);
    }

    public boolean unsetYear() {
        if (isSetYear()) {
            Integer oldYear = year;
            year = null;
            firePropertyChange(PmfConstants.ref_year, oldYear, year);
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
        firePropertyChange(PmfConstants.ref_title, oldTitle, title);
    }

    public boolean unsetTitle() {
        if (isSetTitle()) {
            String oldTitle = title;
            title = null;
            firePropertyChange(PmfConstants.ref_title, oldTitle, title);
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
        firePropertyChange(PmfConstants.ref_abstractText, oldAbstractText, abstractText);
    }

    public boolean unsetAbstractText() {
        if (isSetAbstractText()) {
            String oldAbstractText = abstractText;
            abstractText = null;
            firePropertyChange(PmfConstants.ref_abstractText, oldAbstractText, abstractText);
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
        firePropertyChange(PmfConstants.ref_journal, oldJournal, journal);
    }

    public boolean unsetJournal() {
        if (isSetJournal()) {
            String oldJournal = journal;
            journal = null;
            firePropertyChange(PmfConstants.ref_journal, oldJournal, journal);
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
        firePropertyChange(PmfConstants.ref_volume, oldVolume, volume);
    }

    public boolean unsetVolume() {
        if (isSetVolume()) {
            String oldVolume = volume;
            volume = null;
            firePropertyChange(PmfConstants.ref_volume, oldVolume, volume);
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
        firePropertyChange(PmfConstants.ref_issue, oldIssue, issue);
    }

    public boolean unsetIssue() {
        if (isSetIssue()) {
            String oldIssue = issue;
            issue = null;
            firePropertyChange(PmfConstants.ref_issue, oldIssue, issue);
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
        throw new PropertyUndefinedError(PmfConstants.ref_page, this);
    }

    public boolean isSetPage() {
        return page != null;
    }

    public void setPage(int page) {
        Integer oldPage = this.page;
        this.page = page;
        firePropertyChange(PmfConstants.ref_page, oldPage, page);
    }

    public boolean unsetPage() {
        if (isSetPage()) {
            Integer oldPage = page;
            page = null;
            firePropertyChange(PmfConstants.ref_page, oldPage, page);
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
        throw new PropertyUndefinedError(PmfConstants.ref_approvalMode, this);
    }

    public boolean isSetApprovalMode() {
        return approvalMode != null;
    }

    public void setApprovalMode(int approvalMode) {
        Integer oldApprovalMode = this.approvalMode;
        this.approvalMode = approvalMode;
        firePropertyChange(PmfConstants.ref_approvalMode, oldApprovalMode, approvalMode);
    }

    public boolean unsetApprovalMode() {
        if (isSetApprovalMode()) {
            Integer oldApprovalMode = approvalMode;
            approvalMode = null;
            firePropertyChange(PmfConstants.ref_approvalMode, oldApprovalMode, approvalMode);
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
        firePropertyChange(PmfConstants.ref_website, oldWebsite, website);
    }

    public boolean unsetWebsite() {
        if (isSetWebsite()) {
            String oldWebsite = website;
            website = null;
            firePropertyChange(PmfConstants.ref_website, oldWebsite, website);
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
        firePropertyChange(PmfConstants.ref_type, oldType, type);
    }

    public boolean unsetType() {
        if (isSetType()) {
            ReferenceType oldType = type;
            type = null;
            firePropertyChange(PmfConstants.ref_type, oldType, type);
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
        firePropertyChange(PmfConstants.ref_comment, oldComment, comment);
    }

    public boolean unsetComment() {
        if (isSetComment()) {
            String oldComment = comment;
            comment = null;
            firePropertyChange(PmfConstants.ref_comment, oldComment, comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        final Reference other = (Reference) object;
        return Objects.equals(author, other.author) &&
                Objects.equals(year, other.year) &&
                Objects.equals(title, other.title) &&
                Objects.equals(abstractText, other.abstractText) &&
                Objects.equals(journal, other.journal) &&
                Objects.equals(volume, other.volume) &&
                Objects.equals(issue, other.issue) &&
                Objects.equals(page, other.page) &&
                Objects.equals(approvalMode, other.approvalMode) &&
                Objects.equals(website, other.website) &&
                Objects.equals(type, other.type) &&
                Objects.equals(comment, other.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, year, title, abstractText, journal, volume, issue, page, approvalMode, website,
                type, comment);
    }
}
