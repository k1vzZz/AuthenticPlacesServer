package com.developer.server.authenticplaces.model;

import com.developer.server.authenticplaces.entity.Comment;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;

import java.util.List;

public class OutputContentMarker {

    private User creator;

    private List<Snapshot> snapshots;

    private List<Comment> comments;

    public OutputContentMarker(User creator, List<Snapshot> snapshots, List<Comment> comments) {
        this.creator = creator;
        this.snapshots = snapshots;
        this.comments = comments;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
