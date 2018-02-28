package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberTags;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberTagsRepository;

@Repository
public class MemberTagsRepositoryImpl extends GenericRepositoryImpl<MemberTags, Long> implements MemberTagsRepository {

}
