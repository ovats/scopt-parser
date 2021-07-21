package com.gm.repository

import java.util.UUID

trait Repository[R, T] {
  def create(entity: R): T
  def update(entity: T): T
  def find(id: UUID): Option[T]
  def findAll(): List[T]
  def removeAll(): Unit
}
