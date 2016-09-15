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
        if (attributeName.equals("AU")) {
            author = value;
            return true;
        }
        if (attributeName.equals("PY")) {
            year = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals("TI")) {
            title = value;
            return true;
        }
        if (attributeName.equals("AB")) {
            abstractText = value;
            return true;
        }
        if (attributeName.equals("T2")) {
            journal = value;
            return true;
        }
        if (attributeName.equals("VL")) {
            volume = value;
            return true;
        }
        if (attributeName.equals("IS")) {
            issue = value;
            return true;
        }
        if (attributeName.equals("SP")) {
            page = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals("LB")) {
            approvalMode = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals("UR")) {
            website = value;
            return true;
        }
        if (attributeName.equals("M3")) {
            type = ReferenceType.fromValue(StringTools.parseSBMLInt(value));
            return true;
        }
        if (attributeName.equals("N1")) {
            comment = value;
            return true;
        }

        return false;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();

        if (author != null && !author.isEmpty())
            attributes.put("AU", author);
        if (year != null)
            attributes.put("PY", StringTools.toString(year));
        if (title != null && !title.isEmpty())
            attributes.put("TI", title);
        if (abstractText != null && !abstractText.isEmpty())
            attributes.put("AB", abstractText);
        if (journal != null && !journal.isEmpty())
            attributes.put("T2", journal);
        if (volume != null && !volume.isEmpty())
            attributes.put("VL", volume);
        if (issue != null && !issue.isEmpty())
            attributes.put("IS", issue);
        if (page != null)
            attributes.put("SP", StringTools.toString(page));
        if (approvalMode != null)
            attributes.put("LB", StringTools.toString(approvalMode));
        if (website != null && !website.isEmpty())
            attributes.put("UR", website);
        if (type != null)
            attributes.put("M3", type.name());
        if (comment != null && !comment.isEmpty())
            attributes.put("N1", comment);

        return attributes;
    }

    @Override
    public String toString() {
        return author + "_" + year + "_" + title;
    }
}
