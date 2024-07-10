package com.riderguru.rider_guru.joining_points.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface JoiningPointRepository extends JpaRepository<JoiningPoint, Long>, JpaSpecificationExecutor<JoiningPoint> {
}
