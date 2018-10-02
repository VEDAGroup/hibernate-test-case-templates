/*
 * Copyright (c) 2018 VEDA GmbH. All rights reserved.
 * Use is subject to license terms.
 */

package org.hibernate.envers.bugs;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "templatetopic")
public class InterviewTemplateTopic implements Serializable {

    @Id
    @Column(name="id")
    protected long id;

    // This field is used to capture the value of the column named
    // in the @OrderColumn annotation on the referencing entity.
    // See http://www.solewing.org/blog/2015/07/hibernate-envers-onetomany-list-and-ordercolumn/
    @Column(name = "index")
    private Integer index;

    @Column(name = "description")
    private String description;

    protected InterviewTemplateTopic() {

    }

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy(value = "index")
    @AuditMappedBy(mappedBy = "topic", positionMappedBy = "index")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 100)
    private List<InterviewTemplateItem> items = new ArrayList<>();

    @Column(name = "lastupdate")
    private Long lastUpdate; //No use of LocalDateTime because of https://hibernate.atlassian.net/browse/HHH-9042
}
