package com.blocker.repository;

import com.blocker.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment,Long>{
}
