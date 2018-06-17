package com.developer.server.authenticplaces.model;

import com.developer.server.authenticplaces.entity.Comment;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;

import java.util.ArrayList;
import java.util.List;

public class OutputContentMarker {

    private Integer idCreatorMarker;

    private String identifierClient;

    private String login;

    private String urlImageCreator;

    private List<OutputSnapshot> snapshots;

    private List<OutputComment> comments;

    public OutputContentMarker(User creator) {
        this.idCreatorMarker = creator.getId();
        this.identifierClient = creator.getIdentifierClient();
        this.login = creator.getLogin();
        this.urlImageCreator = creator.getUrl();
        snapshots = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public void setSnapshots(List<Snapshot> snapshotList) {
        if (snapshotList == null) return;
        for (Snapshot snapshot : snapshotList){
            OutputSnapshot outputSnapshot = new OutputSnapshot(snapshot.getAuthorPhoto().getIdentifierClient(),
                    snapshot.getAuthorPhoto().getLogin(), snapshot.getAuthorPhoto().getUrl(),
                    snapshot.getUrl(), snapshot.getUploadDate());
            snapshots.add(outputSnapshot);
        }
    }

    public void setComments(List<Comment> commentList) {
        if (commentList == null) return;
        for (Comment comment : commentList){
            OutputComment outputComment = new OutputComment(comment.getAuthor().getIdentifierClient(),
                    comment.getAuthor().getLogin(), comment.getAuthor().getUrl(), comment.getText(),
                    comment.getUploadDate());
            comments.add(outputComment);
        }
    }
}
