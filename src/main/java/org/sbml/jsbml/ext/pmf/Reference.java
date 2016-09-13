package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
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

        /**
         * @param value Value of the reference type.
         * @return The ReferenceType associated to the value. If value is invalid it will return null.
         */
        public static ReferenceType fromValue(final int value) {
            for (final ReferenceType referenceType : values()) {
                if (referenceType.value == value) {
                    return referenceType;
                }
            }
            return null;
        }
    }

    public String author;
    public Integer year;
    public String title;
    public String abstractText;
    public String journal;
    public String volume;
    public String issue;
    public Integer page;
    public Integer approvalMode;
    public String website;
    public ReferenceType type;
    public String comment;

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
    }

    @Override
    public Reference clone() {
        return new Reference(this);
    }

    /**
     * Clones this class.
     */


    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals(PmfConstants.referenceAuthor)) {
            author = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceYear)) {
            year = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceTitle)) {
            title = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceYear)) {
            abstractText = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceJournal)) {
            journal = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceVolume)) {
            volume = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceIssue)) {
            issue = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.referencePage)) {
            page = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceApproval)) {
            approvalMode = StringTools.parseSBMLInt(value);
            return false;
        }
        if (attributeName.equals(PmfConstants.referenceWebsite)) {
            website = value;
            return false;
        }
        if (attributeName.equals(PmfConstants.referenceType)) {
            type = ReferenceType.fromValue(StringTools.parseSBMLInt(value));
            return true;
        }
        if (attributeName.equals(PmfConstants.referenceComment)) {
            comment = value;
            return true;
        }

        return false;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();

        if (author != null && !author.isEmpty())
            attributes.put(PmfConstants.referenceAuthor, author);
        if (year != null)
            attributes.put(PmfConstants.referenceYear, StringTools.toString(year));
        if (title != null && !title.isEmpty())
            attributes.put(PmfConstants.referenceTitle, title);
        if (abstractText != null && !abstractText.isEmpty())
            attributes.put(PmfConstants.referenceAbstract, abstractText);
        if (journal != null && !journal.isEmpty())
            attributes.put(PmfConstants.referenceJournal, journal);
        if (volume != null && !volume.isEmpty())
            attributes.put(PmfConstants.referenceVolume, volume);
        if (issue != null && !issue.isEmpty())
            attributes.put(PmfConstants.referenceIssue, issue);
        if (page != null)
            attributes.put(PmfConstants.referencePage, StringTools.toString(page));
        if (approvalMode != null)
            attributes.put(PmfConstants.referenceApproval, StringTools.toString(approvalMode));
        if (website != null && !website.isEmpty())
            attributes.put(PmfConstants.referenceWebsite, website);
        if (type != null)
            attributes.put(PmfConstants.referenceType, type.name());
        if (comment != null && !comment.isEmpty())
            attributes.put(PmfConstants.referenceComment, comment);

        return attributes;
    }

    @Override
    public String toString() {
        return author + "_" + year + "_" + title;
    }
}
