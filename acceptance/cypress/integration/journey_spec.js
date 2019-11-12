context('Journey spec', () => {
    it('User can open ', () => {
        cy.visit('localhost:3000')

        cy.get('[data-cy=MessageTextField]')
          .click()
          .type('Wow, this is such an amazing app - we should hire this guy!')
        cy.get('button[value="Submit"]').click()

        cy.contains('Wow, this is such an amazing app - we should hire this guy!').should('exist')
    })
})