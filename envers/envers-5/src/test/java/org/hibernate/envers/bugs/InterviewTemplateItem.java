/*
 * Copyright (c) 2018. VEDA GmbH. All rights reserved.
 * Use is subject to license terms.
 */

package org.hibernate.envers.bugs;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
@Table(name = "interviewtemplateitem")
public class InterviewTemplateItem implements Serializable {

    @Id
    @Column(name = "id")
    protected long id;

    // This field is used to capture the value of the column named
    // in the @OrderColumn annotation on the referencing entity.
    // See http://www.solewing.org/blog/2015/07/hibernate-envers-onetomany-list-and-ordercolumn/
    @Column(name = "index")
    private Integer index;

    @JoinColumn(name = "topicid")
    @ManyToOne
    private InterviewTemplateTopic topic;

    @Column(name = "lastupdate")
    private Long lastUpdate; //No use of LocalDateTime because of https://hibernate.atlassian.net/browse/HHH-9042

    @Column(name = "label")
    private String label;
}
